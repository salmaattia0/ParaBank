package Tests;

import Pages.BillPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class BillTest extends BaseTest {

    @Test
    public void payBillTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("oliviaB03");
        loginPage.enterPassword("MyPass2025");
        loginPage.clickLogin();

        BillPage billPayPage = new BillPage(driver);
        billPayPage.navigateToBillPay();
//      billPayPage.enterPayeeDetails(name, address, city, state, zip, phone, acc, amount);
        billPayPage.enterPayeeDetails("Ali", "Ca", "Cairo", "Eg",
                "123456", "1023654", "14323", "100");
        billPayPage.sendPayment();
        Assert.assertFalse(billPayPage.getConfirmationMessage().contains("Bill Payment Complete"));
    }
}
