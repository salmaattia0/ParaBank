package Tests;

import Pages.BillPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.PropertiesReader;

public class BillTest extends BaseTest {

    BillPage billPayPage;

    @Test(dataProvider = "billPayData", dataProviderClass = ExcelReader.class)
    public void payBillTest(String payeeName, String address, String account, String amount) {
        billPayPage = new BillPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        billPayPage.navigateToBillPay();
        billPayPage.enterPayeeDetails(payeeName, address, account);
        billPayPage.enterAmount(amount);
        billPayPage.sendPayment();

        Assert.assertTrue(billPayPage.getConfirmationMessage().contains(amount),
                "Confirmation message does not contain correct amount!");

        billPayPage.navigateToAccountsOverview();
        Assert.assertTrue(billPayPage.isTransactionRecorded(amount),
                "Transaction not recorded in Accounts Overview!");
    }

    @Test
    public void payBillWithoutPayeeName() {
        billPayPage = new BillPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        billPayPage.navigateToBillPay();
        billPayPage.enterPayeeDetails("", "Cairo Street", "123456");
        billPayPage.enterAmount("100");
        billPayPage.sendPayment();

        Assert.assertTrue(billPayPage.getErrorMessage().contains("Payee name is required"),
                "Missing payee name error not displayed!");
    }

    @Test
    public void payBillWithInvalidAccountNumber() {
        billPayPage = new BillPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        billPayPage.navigateToBillPay();
        billPayPage.enterPayeeDetails("Electric Company", "Main Street", "ABCDEF");
        billPayPage.enterAmount("200");
        billPayPage.sendPayment();

        Assert.assertTrue(billPayPage.getErrorMessage().contains("Invalid account number"),
                "Invalid account number error not displayed!");
    }

    @Test
    public void payBillWithoutAmount() {
        billPayPage = new BillPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        billPayPage.navigateToBillPay();
        billPayPage.enterPayeeDetails("Water Company", "Nile Street", "654321");
        billPayPage.enterAmount(""); // Empty amount
        billPayPage.sendPayment();

        Assert.assertTrue(billPayPage.getErrorMessage().contains("Amount is required"),
                "Missing amount error not displayed!");
    }
}
