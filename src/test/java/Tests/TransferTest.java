package Tests;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.TransferPage;
import utils.JSONReader;
import utils.PropertiesReader;

public class TransferTest extends BaseTest {

    @Test(dataProvider = "transferData", dataProviderClass = JSONReader.class)
    public void transferFundsTest(JSONReader.TransferData data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();
        TransferPage transferPage = new TransferPage(driver);
        transferPage.enterAmount(data.getAmount());
        transferPage.selectFromAccount(data.getFromAccount());
        transferPage.selectToAccount(data.getToAccount());
        transferPage.clickTransfer();
        String confirmation = transferPage.getConfirmationMessage();
        Assert.assertTrue(confirmation.contains(data.getAmount()), "Amount mismatch!");
        Assert.assertTrue(confirmation.contains(data.getFromAccount()), "From Account mismatch!");
        Assert.assertTrue(confirmation.contains(data.getToAccount()), "To Account mismatch!");
    }
}
