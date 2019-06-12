package com.macmil.framework.browser.dsl.window;

import com.google.common.collect.Iterables;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Window {
    private static final long NEW_WINDOW_WAIT = 10;
    private static final long TIMEOUT = 20;

    private WebDriver driver;

    public Window(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewWindow() {
        driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
    }

    public void waitForNewWindow() {
        new WebDriverWait(driver, TIMEOUT).until(d -> !d.getWindowHandle().equals(Iterables.getLast(d.getWindowHandles())));
        waitForWindowAction(NEW_WINDOW_WAIT);
    }

    public void waitForWindowAction(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
