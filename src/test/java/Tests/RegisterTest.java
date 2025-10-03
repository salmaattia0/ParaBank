package Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import Pages.RegisterPage;


public class RegisterTest extends BaseTest {
    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return new Object[][] {
                {
                        "Emma", "Johnson", "123 Oak St", "Austin", "TX", "73301",
                        "(512) 555-0123", "111-22-3333",
                        "emmaJ01", "Password123", "Password123"
                },

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
