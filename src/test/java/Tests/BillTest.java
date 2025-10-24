package Tests;

import Pages.BillPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.PropertiesReader;

public class BillTest extends BaseTest {
    @BeforeClass
    public void Load() {
        PropertiesReader.loadProperties("src/main/resources/LoginData.properties");
    }

    @Test(dataProvider = "billPayData", dataProviderClass = ExcelReader.class)
    public void payBillTest(String amount) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(PropertiesReader.getProperty("valid.username"));
        loginPage.enterPassword(PropertiesReader.getProperty("valid.password"));
        loginPage.clickLogin();

        BillPage billPayPage = new BillPage(driver);
        billPayPage.navigateToBillPay();
        billPayPage.enterPayeeDetails("Ali", "Egypt", "Cairo", "Cairo",
                "123456", "01023654658", "14323");

        billPayPage.selectFromAccount(0);
        billPayPage.enterAmount(amount);
        billPayPage.sendPayment();

        String confirmation = billPayPage.getConfirmationMessage();
        Assert.assertTrue(confirmation.contains("Bill Payment Complete"),
                "Expected 'Bill Payment Complete' message not found!");
    }
}
