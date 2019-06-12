package com.macmil.framework.browser.dsl.elements;

import com.google.common.base.Preconditions;
import com.macmil.framework.webdriver.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageElement {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageElement.class);
    private static final long TIMEOUT = 20;

    private WebElement webElement;
    private WebDriver driver;

    public WebElement getWebElement() { return webElement; }

    public PageElement(WebElement webElement) {
        this.webElement = webElement;
        driver = DriverManager.getInstance().getDriver();
    }

    public PageElement click() {
        Preconditions.checkState(isEnabled(), "Element " + webElement + " could not be clicked, since it's not enabled!");
        focus();
        webElement.click();
        return this;
    }

    public PageElement focus() {
        new Actions(driver).moveToElement(webElement).perform();
        return this;
    }

    public PageElement waitForDisplayed() {
        new WebDriverWait(driver, TIMEOUT).until(input -> isDisplayed());
        return this;
    }

    public PageElement waitForNotDisplayed() {
        new WebDriverWait(driver, TIMEOUT).until(input -> !isDisplayed());
        return this;
    }

    public PageElement waitForEnabled() {
        new WebDriverWait(driver, TIMEOUT).until(input -> isEnabled());
        return this;
    }

    public boolean isEnabled() {
        boolean retval;
        try {
            retval = webElement.isEnabled();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            LOGGER.debug("Element is not enabled!", e);
            retval = false;
        }

        return retval;
    }

    public boolean isDisplayed() {
        boolean retval;
        try {
            retval = webElement.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            LOGGER.debug("Element is not displayed!", e);
            retval = false;
        }

        return retval;
    }

    public PageElement and() { return this; }
}
