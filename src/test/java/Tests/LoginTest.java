package Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import Pages.LoginPage;
import utils.PropertiesReader;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        PropertiesReader.loadProperties("src/main/resources/LoginData.properties");
    }

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void validLoginTest_AndLogout() {
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        String overviewText = loginPage.getAccountOverviewMessage();
        Assert.assertTrue(overviewText.contains("Accounts Overview"),
                "Expected 'Accounts Overview' message not found!");

        loginPage.clickLogout();
    }

    @Test(priority = 2)
    public void invalidLoginTest_InvalidUsernameAndPassword() {
        loginPage.enterUsername(PropertiesReader.getProperty("invalid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("invalid.password"));
        loginPage.clickLogin();

        String errorText = loginPage.getInvalidMessageText();
        Assert.assertTrue(errorText.contains("The username and password could not be verified."),
                "Invalid credentials message not displayed!");
    }

    @Test(priority = 3)
    public void invalidLoginTest_InvalidUsernameOnly() {
        loginPage.enterUsername(PropertiesReader.getProperty("invalid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        String errorText = loginPage.getInvalidMessageText();
        Assert.assertTrue(errorText.contains("The username and password could not be verified."),
                "Invalid username message not displayed!");
    }

    @Test(priority = 4)
    public void invalidLoginTest_InvalidPasswordOnly() {
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("invalid.password"));
        loginPage.clickLogin();

        String errorText = loginPage.getInvalidMessageText();
        Assert.assertTrue(errorText.contains("The username and password could not be verified."),
                "Invalid password message not displayed!");
    }

    @Test(priority = 5)
    public void invalidLoginTest_EmptyUsername() {
        loginPage.enterUsername("");
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        String emptyText = loginPage.getEmptyMessageText();
        Assert.assertTrue(emptyText.contains("Please enter a username and password."));
    }

    @Test(priority = 6)
    public void invalidLoginTest_EmptyPassword() {
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String emptyText = loginPage.getEmptyMessageText();
        Assert.assertTrue(emptyText.contains("Please enter a username and password."));
    }

    @Test(priority = 7)
    public void invalidLoginTest_EmptyUsernameAndPassword() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String emptyText = loginPage.getEmptyMessageText();
        Assert.assertTrue(emptyText.contains("Please enter a username and password."));
    }
}
