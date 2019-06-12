package com.macmil.framework.model.pages.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageMob extends HomePage {
    @FindBy(css = ".mobile-header-top")
    private WebElement mobileHeader;

    @FindBy(css = ".mobile-nav")
    private WebElement mobilenav;

    @FindBy(css = ".hamburger")
    private WebElement mobileMenuButton;

    @FindBy(css = ".account-links a[href='/signup']")
    private WebElement createAccountButton;

    @FindBy(css = "nav .primary a")
    private List<WebElement> primaryMenuItems;

    @FindBy(css = ".warning-container .accept")
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
        getBrowser().getElement(mobilenav).waitForDisplayed();
        primaryMenuItems.stream()
                .filter(item -> item.getText().equals(PRICES))
                .findFirst()
                .ifPresent(l -> getBrowser().getLink(l).waitForEnabled().and().click());
        getBrowser().switchToNewWindow();
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
