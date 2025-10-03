package Tests;

import Pages.LoginPage;
import Pages.TransferPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JSONReader;

public class TransferTest extends BaseTest {

    @Test(dataProvider = "transferData", dataProviderClass = JSONReader.class)
    public void transfer(JSONReader.TransferData data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("johnD01");
        loginPage.enterPassword("Pass1234");
        loginPage.clickLogin();

        TransferPage transferPage = new TransferPage(driver);
        transferPage.navigateToTransferPage();
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
