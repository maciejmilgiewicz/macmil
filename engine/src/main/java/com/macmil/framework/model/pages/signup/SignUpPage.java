package com.macmil.framework.model.pages.signup;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.pages.factory.MultiVariantPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignUpPage extends BasePageObject<SignUpPage> {
    @FindBy(css = "input[data-testid='signup-email-field']")
    private WebElement emailField;

    @FindBy(css = "input[data-testid='signup-username-field']")
    private WebElement usernameField;

    @FindBy(css = "input[data-testid='signup-password-field']")
    private WebElement passwordField;

    @FindBy(css = ".Select .Select-arrow")
    private WebElement courtyOfResidenceDropDownArrow;

    @FindBy(css = ".Select-menu-outer")
    private WebElement countriesList;

    @FindBy(css = ".Select-option")
    private List<WebElement> countriesOfResidence;

    @FindBy(css = ".input-checkbox")
    private WebElement termsCheckbox;

    @FindBy(css = "button[data-testid='confirm button']")
    private WebElement createAccountButton;

    public static SignUpPage open(String url) {
        getBrowser().open(url);
        return waitForPage();
    }

    public static SignUpPage waitForPage() {
        return new MultiVariantPageObjectFactory().create(SignUpPage.class);
    }

    public  SignUpPage enterEmail(String email) {
        getBrowser().getTextField(emailField).type(email);
        return this;
    }

    public  SignUpPage enterUsername(String username) {
        getBrowser().getTextField(usernameField).type(username);
        return this;
    }

    public  SignUpPage enterPassword(String password) {
        getBrowser().getTextField(passwordField).type(password);
        return this;
    }

    public SignUpPage selectCountryOfResidence() {
        getBrowser().getElement(courtyOfResidenceDropDownArrow).click();
        getBrowser().getElement(countriesList).waitForDisplayed();
        countriesOfResidence.stream().findAny().ifPresent(WebElement::click);
        return this;
    }

    public SignUpPage agreeToTerms() {
        getBrowser().getCheckBox(termsCheckbox).check();
        return this;
    }

    public boolean isCreateAccountButtonEnabled() {
        return getBrowser().getButton(createAccountButton).isEnabled();
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getTextField(emailField).waitForEnabled();
        return getBrowser().getButton(createAccountButton).isDisplayed();
    }
}
