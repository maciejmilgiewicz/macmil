package com.macmil.uitests;

import com.macmil.framework.webdriver.DriverManager;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/features",
        tags = {"@UITests"},
        format = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);

    @BeforeSuite
    public void startSuite() {
        LOGGER.info("Test suite started");
        DriverManager.getInstance().getDriver();
    }

    @AfterSuite
    public void stopSuite() {
        DriverManager.getInstance().destroyDriver();
        LOGGER.info("Test suite finished");
    }
}
