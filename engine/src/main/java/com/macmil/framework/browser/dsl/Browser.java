package com.macmil.framework.browser.dsl;

import com.macmil.framework.browser.dsl.elements.CheckBox;
import com.macmil.framework.browser.dsl.elements.PageElement;
import com.macmil.framework.browser.dsl.elements.TextField;
import com.macmil.framework.browser.dsl.window.Window;
import com.macmil.framework.webdriver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
    private static final long TIMEOUT = 60;

    private WebDriver driver;
    private Window window;

    public Browser() {
        driver = DriverManager.getInstance().getDriver();
        window = new Window(driver);
    }

    public PageElement getElement(WebElement webElement) { return new PageElement(webElement); }

    public PageElement getButton(WebElement webElement) { return new PageElement(webElement); }

    public PageElement getLink(WebElement webElement) {
        return new PageElement(webElement);
    }

    public TextField getTextField(WebElement webElement) { return new TextField(webElement); }

    public CheckBox getCheckBox(WebElement webElement) { return new CheckBox(webElement); }

    public void open(String url) { driver.get(url); }

    public void switchToNewWindow() {
        window.waitForNewWindow();
        window.switchToNewWindow();
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, TIMEOUT).until(input -> isPageComplete(getReadyState(driver)));
    }

    private boolean isPageComplete(String readyState) {
        return "complete".equals(readyState);
    }

    private String getReadyState(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.readyState;");
    }
}
