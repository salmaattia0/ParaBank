package Tests;

import Pages.LoginPage;
import Pages.NewAccountPage;
import Pages.TransferPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JSONReader;
import utils.PropertiesReader;

public class TransferTest extends BaseTest {

    @BeforeClass
    public void Load() {
        PropertiesReader.loadProperties("src/main/resources/LoginData.properties");
    }

    @Test(dataProvider = "transferData", dataProviderClass = JSONReader.class)
    public void transfer(JSONReader.TransferData data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        NewAccountPage newAccountPage = new NewAccountPage(driver);
        newAccountPage.navigateToOpenNewAccount();
        newAccountPage.selectAccountType("SAVINGS");
        newAccountPage.selectFromAccount(0);
        newAccountPage.createAccount();

        TransferPage transferPage = new TransferPage(driver);
        transferPage.navigateToTransferPage();
        transferPage.enterAmount(data.getAmount());
        transferPage.selectFromAccount(0);
        transferPage.selectToAccount(1);
        transferPage.clickTransfer();

        String confirmation = transferPage.getConfirmationMessage();
        Assert.assertTrue(confirmation.contains("Transfer Complete!"),
                "Expected 'Transfer Complete!' message not found!");
    }

}
