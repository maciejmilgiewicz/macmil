package com.macmil.uitests;

import com.macmil.framework.config.ConfigFactory;
import com.macmil.framework.model.pages.homepage.HomePage;
import com.macmil.framework.model.pages.marketspage.MarketsPage;
import com.macmil.framework.model.pages.signup.SignUpPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class UITests {

    @Given("^i land on the home page$")
    public void iLandOnTheHomePage() {
        HomePage.open(ConfigFactory.getConfig().getTestUrl());
    }

    @When("^i navigate to sign up page$")
    public void iNavigateToSignUpPage() {
        HomePage.waitForPage().and().clickCreateAccountButton();
    }

    @When("^i enter email as \"([^\"]*)\"$")
    public void iEnterEmail(String email) {
        SignUpPage.waitForPage().and().enterEmail(email);
    }

    @When("^i enter username as \"([^\"]*)\"$")
    public void iEnterUsername(String username) {
        SignUpPage.waitForPage().and().enterUsername(username);
    }

    @When("^i enter password as \"([^\"]*)\"$")
    public void iEnterPassword(String password) {
        SignUpPage.waitForPage().and().enterPassword(password);
    }

    @When("^i choose a country of residence$")
    public void iChooseACountryOfResidence() {
        SignUpPage.waitForPage().and().selectCountryOfResidence();
    }

    @When("^i accept terms$")
    public void iAcceptTerms() {
        SignUpPage.waitForPage().and().agreeToTerms();
    }

    @Then("^create account button should be enabled$")
    public void createAccountButtonShouldBeEnabled() {
        Assert.assertTrue(SignUpPage.waitForPage().and().isCreateAccountButtonEnabled(), "Create account button is not enabled");
    }

    @When("^i navigate to prices page$")
    public void iNavigateToPricesPage() {
        HomePage.waitForPage().clickPricesLink();
    }

    @When("^i filter for asset \"([^\"]*)\"$")
    public void iFilterForAsset(String asset) {
        MarketsPage.waitForPage().and().filterAsset(asset);
    }

    @Then("^all prices in the table should be more than zero$")
    public void allPricesInTheTableShouldBeMoreThanZero() {
        Assert.assertTrue(MarketsPage.waitForPage().and().areAllPricesMoreThanZero(), "Assets with prices that are less or equal to zero exist");
    }
}
