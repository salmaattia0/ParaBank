package Tests;

import Pages.LoginPage;
import Pages.NewAccountPage;
import org.testng.annotations.Test;
import utils.CSVReader;

public class NewAccountTest extends BaseTest {

    @Test(dataProvider = "openNewAccountData", dataProviderClass = CSVReader.class)
    public void openNewAccountTest(String accountType, String fundingAccount) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("oliviaB03");
        loginPage.enterPassword("MyPass2025");
        loginPage.clickLogin();

        NewAccountPage accountPage = new NewAccountPage(driver);
        accountPage.navigateToOpenNewAccount();
        accountPage.selectAccountType(accountType);
        accountPage.selectFromAccount(fundingAccount);
        accountPage.createAccount();
    }
}
