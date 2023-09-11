package com.macmil.framework.model.pages.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageMob extends HomePage {
    @FindBy(css = ".mobile-header-top")
    private WebElement mobileHeader;

    @FindBy(css = ".hamburger")
    private WebElement mobileMenuButton;

    @FindBy(css = "a[data-testid='sign-up']")
    private WebElement createAccountButton;

    @FindBy(css = "a[href='/prices']")
    private WebElement pricesLink;

    @FindBy(css = ".kraken-cookie-warning button")
    private WebElement cookiesAcceptButton;

    @Override
    public  HomePage clickCreateAccountButton() {
        acceptCookies();
        getBrowser().getButton(createAccountButton).click();
        return this;
    }

    @Override
    public HomePage clickPricesLink() {
        getBrowser().getButton(mobileMenuButton).click();
        getBrowser().getLink(pricesLink).waitForEnabled().and().click();
        return this;
    }

    private void acceptCookies() {
        if(getBrowser().getButton(cookiesAcceptButton).isDisplayed()) {
            getBrowser().getButton(cookiesAcceptButton).click().and().waitForNotDisplayed();
        }
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getButton(createAccountButton).waitForEnabled();
        return getBrowser().getElement(mobileHeader).isDisplayed();
    }
}
