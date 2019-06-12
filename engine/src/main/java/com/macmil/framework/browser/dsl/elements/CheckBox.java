package com.macmil.framework.browser.dsl.elements;

import org.openqa.selenium.WebElement;

public class CheckBox extends PageElement {
    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public boolean isChecked() {
        return getWebElement().isSelected();
    }

    public CheckBox setChecked(boolean checked) {
        if (isChecked() != checked) {
            getWebElement().click();
        }
        return this;
    }

    public CheckBox check() {
        return setChecked(true);
    }

    public CheckBox uncheck() {
        return setChecked(false);
    }
}
