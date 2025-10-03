package Tests;

import Pages.LoginPage;
import Pages.NewAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesReader;
import utils.CSVReader;

public class NewAccountTest extends BaseTest {

    @Test(dataProvider = "openNewAccountData", dataProviderClass = CSVReader.class)
    public void openNewAccountTest(String accountType, String fundingAccount, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        driver.findElement(org.openqa.selenium.By.linkText("Open New Account")).click();
        NewAccountPage accountPage = new NewAccountPage(driver);

        accountPage.selectAccountType(accountType);
        accountPage.selectFundingAccount(fundingAccount);

        accountPage.clickOpenNewAccount();

        if (expectedResult.equalsIgnoreCase("SUCCESS")) {
            String newAccountId = accountPage.getNewAccountId();
            Assert.assertNotNull(newAccountId, "New Account ID not generated!");

            accountPage.goToAccountsOverview();
            Assert.assertTrue(accountPage.isAccountPresentInOverview(newAccountId),
                    "New account not found in Accounts Overview.");
        } else if (expectedResult.equalsIgnoreCase("ERROR")) {
            Assert.assertTrue(accountPage.isErrorMessageDisplayed(),
                    "Expected error message but none was displayed.");
        } else {
            Assert.fail("Invalid expectedResult value in test data: " + expectedResult);
        }
    }
}
