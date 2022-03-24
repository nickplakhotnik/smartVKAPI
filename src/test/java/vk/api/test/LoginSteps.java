package vk.api.test;

import model.TestUser;
import org.testng.Assert;
import pages.EnterPage;
import pages.NewsPage;

public class LoginSteps {

    public static void loginStep(TestUser user) {

        EnterPage enterPage = new EnterPage();
        Assert.assertTrue(enterPage.state().waitForDisplayed(), "EnterPage is not open");

        enterPage.sendLogin(user.getLogin());
        enterPage.sendPassword(user.getPassword());
        enterPage.clickOnSingInButton();
        NewsPage newsPage = new NewsPage();
        Assert.assertTrue(newsPage.state().waitForDisplayed(), "MainPage is not open");
    }
}
