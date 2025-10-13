package Tests;

import Pages.LoginPage;
import Pages.NewAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.CSVReader;
import utils.PropertiesReader;

public class NewAccountTest extends BaseTest {
    @BeforeClass
    public void Load() {
        PropertiesReader.loadProperties("src/main/resources/LoginData.properties");
    }

    @Test(dataProvider = "openNewAccountData", dataProviderClass = CSVReader.class)
    public void openNewAccountTest(String accountType) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        NewAccountPage accountPage = new NewAccountPage(driver);
        accountPage.navigateToOpenNewAccount();
        accountPage.selectAccountType(accountType);
        accountPage.selectFromAccount(0);
        accountPage.createAccount();

        String confirmMsg = accountPage.getSuccessText();
        Assert.assertTrue(confirmMsg.contains("Account Opened!"),
                "Expected 'Accounts Overview' message not found!");

        String newAccountNumber = accountPage.getNewAccountNumber();
        accountPage.navigateToAccountsOverview();
        Assert.assertTrue(accountPage.isAccountDisplayed(newAccountNumber),
                "New account not found in Accounts Overview!");

        accountPage.openAccountDetails(newAccountNumber);
        String displayedType = accountPage.getDisplayedAccountType();
        String displayedBalance = accountPage.getDisplayedBalance();
        Assert.assertTrue(displayedType.contains(accountType),
                "Displayed account type does not match the selected type!");
        Assert.assertTrue(displayedBalance.contains("100.00"),
                "Displayed balance is not $100.00!");

    }
}
