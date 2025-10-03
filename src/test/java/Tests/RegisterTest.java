package Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import Pages.RegisterPage;


public class RegisterTest extends BaseTest {
    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return new Object[][]{
                {
                        "Olivia", "Brown", "789 Pine St", "Houston",
                        "TX", "77002", "(713) 555-0145", "333-44-5555",
                        "oliviaB03", "MyPass2025", "MyPass2025"
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
