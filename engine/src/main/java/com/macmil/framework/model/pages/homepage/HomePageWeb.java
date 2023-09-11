package com.macmil.framework.model.pages.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageWeb extends HomePage {
    @FindBy(css = "nav")
    private WebElement topnav;

    @FindBy(css = "nav a")
    private List<WebElement> primaryMenuItems;

    @FindBy(css = "li[data-testid='top-nav-Prices'] a[href='/prices']")
    private WebElement pricesLink;

    @FindBy(css = "a[data-testid='sign-up']")
    private WebElement createAccountButton;

    @Override
    public  HomePage clickCreateAccountButton() {
        getBrowser().getButton(createAccountButton).waitForEnabled().and().click();
        return this;
    }

    @Override
    public HomePage clickPricesLink() {
        getBrowser().getLink(pricesLink).waitForEnabled().and().click();
        return this;
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getButton(createAccountButton).waitForEnabled();
        return getBrowser().getElement(topnav).isDisplayed();
    }
}
