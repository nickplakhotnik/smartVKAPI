package vk.api.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import configuration.Configuration;
import model.RestConfig;
import model.TestData;
import model.TestUser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.restApi.Specifications;

public class BaseTest {

    protected Logger logger = AqualityServices.getLogger();
    protected RestConfig config = new RestConfig(new JsonSettingsFile("restConfigs.json"));
    protected TestUser user = new TestUser(new JsonSettingsFile("user.json"));
    protected TestData testData = new TestData(new JsonSettingsFile("testData.json"));

    @BeforeMethod
    public void beforeMethod() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
        Specifications.instalSpecification(Specifications.requestSpecification());
    }

    @AfterMethod
    public void afterMethod() {
        AqualityServices.getBrowser().quit();
    }
}
