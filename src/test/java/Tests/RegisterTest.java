package Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import Pages.RegisterPage;


public class RegisterTest extends BaseTest {
    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return new Object[][]{
                {
                        "John", "Doe", "123 Main St", "New York",
                        "NY", "10001", "(212) 555-0123", "111-22-3333",
                        "johnD01", "Pass1234", "Pass1234"
                }
        };
    }

    @Test(dataProvider = "registrationData")
    public void registerAccount(
            String firstName, String lastName, String address,
            String city, String state, String zip, String phone,
            String ssn, String username, String password, String confirmPassword
    )
    {
        RegisterPage RegisterObj = new RegisterPage(driver);
        RegisterObj.goToRegisterPage();
        RegisterObj.fillRegistrationForm(firstName, lastName, address, city, state, zip, phone, ssn, username, password, confirmPassword);
        RegisterObj.submitRegistration();

        Assert.assertTrue(RegisterObj.isRegistrationSuccessful(),
                "Registration failed for: " + username);

        RegisterObj.logout();
    }
}
