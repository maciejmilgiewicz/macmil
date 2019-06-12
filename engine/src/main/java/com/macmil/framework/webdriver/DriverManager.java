package com.macmil.framework.webdriver;

import com.macmil.framework.config.Config;
import com.macmil.framework.config.ConfigFactory;
import com.macmil.framework.browser.BrowserType;
import com.macmil.framework.browser.BrowserViewport;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    private static final DriverManager INSTANCE = new DriverManager();

    private static ThreadLocal<WebDriver> registry = new ThreadLocal<>();

    private DriverManager() {
    }

    public static DriverManager getInstance() { return INSTANCE; }

    public WebDriver getDriver() {
        WebDriver driver = registry.get();

        if (driver == null) {
            Config config = ConfigFactory.getConfig();
            driver = createDriver(config);
            registry.set(driver);
            setBrowserViewport(driver, getBrowserViewport(config));
        }

        return driver;
    }

    private WebDriver createDriver(Config config) {
        WebDriver retval;

        LOGGER.info("Creating WebDriver instance [driver: {} , browser: {}]", config.getRemoteUri(), config.getBrowserType());
        retval = BrowserType.valueOf(config.getBrowserType()).createDriver(config);
        LOGGER.info("WebDriver instance created successfully");

        return retval;
    }

    private void setBrowserViewport(WebDriver driver, BrowserViewport viewport) {
        LOGGER.info("Setting browser viewport to {}", viewport);
        Optional<Dimension> windowSize = viewport.getDimension();
        if (windowSize.isPresent()) {
            driver.manage().window().setSize(windowSize.get());
        } else {
            try {
                driver.manage().window().maximize();
            } catch(WebDriverException e) {
                LOGGER.warn("WebDriver issue while maximizing browser window");
            }
        }
    }

    private BrowserViewport getBrowserViewport(Config config) {
        return BrowserViewport.valueOf(config.getBrowserViewport());
    }

    public void destroyDriver() {
        WebDriver driver = registry.get();

        if (driver != null) {
            LOGGER.info("Destroying WebDriver instance");

            try {
                driver.quit();
            } catch (WebDriverException e) {
                LOGGER.debug("Exception occured while closing browser!", e);
            }
            registry.remove();

            LOGGER.info("WebDriver instance destroyed successfully.");
        } else {
            LOGGER.warn("WebDriver instance is not available, unable to destroy!");
        }
    }
}
