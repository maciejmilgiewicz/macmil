package com.macmil.framework.model;

import com.macmil.framework.browser.dsl.Browser;
import com.macmil.framework.webdriver.DriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BasePageObject<V> implements PageVariant {
    private static Browser browser;

    public WebDriver getDriver() { return DriverManager.getInstance().getDriver(); }

    public static Browser getBrowser() {
        if(browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    @SuppressWarnings(value = "unchecked")
    public V and() {
        return (V) this;
    }
}
