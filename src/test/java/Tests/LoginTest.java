package Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import Pages.LoginPage;
import utils.PropertiesReader;

public class LoginTest extends BaseTest {

    @BeforeClass
    public void Load() {
        PropertiesReader.loadProperties("src/main/resources/LoginData.properties");
    }

    @Test
    public void validLoginTest_AndLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isAtAccountsOverview(),
                "Valid login failed - user was not redirected to Accounts Overview.");
        loginPage.clickLogout();
    }

    @Test
    public void invalidLoginTest_InvalidUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("invalid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("invalid.password"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "The username and password could not be verified.");
    }

    @Test
    public void invalidLoginTest_InvalidUsernameOnly() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("invalid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "The username and password could not be verified.");
    }

    @Test
    public void invalidLoginTest_InvalidPasswordOnly() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("invalid.password"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "The username and password could not be verified.");
    }

    @Test
    public void invalidLoginTest_EmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Please enter a username and password.");
    }

    @Test
    public void invalidLoginTest_EmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Please enter a username and password.");
    }

    @Test
    public void invalidLoginTest_EmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Please enter a username and password.");
    }
}
