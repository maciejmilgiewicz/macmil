package com.macmil.framework.browser.dsl.elements;

import org.openqa.selenium.WebElement;

public class TextField extends PageElement {
    public TextField(WebElement webElement) {
        super(webElement);
    }

    public void type(String text) {
        if (text != null) {
            focus();
            getWebElement().clear();
            getWebElement().sendKeys(text);
        }
    }
}
