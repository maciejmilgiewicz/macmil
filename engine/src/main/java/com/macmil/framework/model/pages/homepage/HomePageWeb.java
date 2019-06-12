package com.macmil.framework.model.pages.homepage;

import com.macmil.framework.browser.dsl.elements.PageElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageWeb extends HomePage {
    @FindBy(css = ".top-nav")
    private WebElement topnav;

    @FindBy(css = "nav .primary a")
    private List<WebElement> primaryMenuItems;

    @FindBy(css = "nav a[href='/signup']")
    private WebElement createAccountButton;

    @Override
    public  HomePage clickCreateAccountButton() {
        getBrowser().getButton(createAccountButton).click();
        return this;
    }

    @Override
    public HomePage clickPricesLink() {
        primaryMenuItems.stream()
                .filter(item -> item.getText().equals(PRICES))
                .findFirst()
                .ifPresent(l -> getBrowser().getLink(l).waitForEnabled().and().click());
        getBrowser().switchToNewWindow();
        return this;
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getButton(createAccountButton).waitForEnabled();
        return getBrowser().getElement(topnav).isDisplayed();
    }
}
