package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BillPage {

    private WebDriver driver;

    private By billPayLink = By.xpath("//a[text()='Bill Pay']");
    private By payeeName = By.xpath("//input[@name='payee.name']");
    private By payeeAddress = By.xpath("//input[@name='payee.address.street']");
    private By payeeCity = By.xpath("//input[@name='payee.address.city']");
    private By payeeState = By.xpath("//input[@name='payee.address.state']");
    private By payeeZipCode = By.xpath("//input[@name='payee.address.zipCode']");
    private By payeePhone = By.xpath("//input[@name='payee.phoneNumber']");
    private By payeeAccount = By.xpath("//input[@name='payee.accountNumber']");
    private By confirmAcc = By.xpath("//input[@name='verifyAccount']");
    private By amount = By.xpath("//input[@name='amount']");
    private By clickSend = By.xpath("//input[@value='Send Payment']");
    private By successMessage = By.xpath("//h1[contains(text(),'Bill Payment Complete')]");
    private By errorMessage = By.xpath("//span[@class='error' or contains(text(),'required') or contains(text(),'Invalid')]");

    public BillPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToBillPay() {
        driver.findElement(billPayLink).click();
    }

    public void enterPayeeDetails(String name, String address, String city, String state,
                                  String zip, String phone, String acc, String amountValue) {
        driver.findElement(payeeName).clear();
        driver.findElement(payeeName).sendKeys(name);

        driver.findElement(payeeAddress).clear();
        driver.findElement(payeeAddress).sendKeys(address);

        driver.findElement(payeeCity).clear();
        driver.findElement(payeeCity).sendKeys(city);

        driver.findElement(payeeState).clear();
        driver.findElement(payeeState).sendKeys(state);

        driver.findElement(payeeZipCode).clear();
        driver.findElement(payeeZipCode).sendKeys(zip);

        driver.findElement(payeePhone).clear();
        driver.findElement(payeePhone).sendKeys(phone);

        driver.findElement(payeeAccount).clear();
        driver.findElement(payeeAccount).sendKeys(acc);

        driver.findElement(confirmAcc).clear();
        driver.findElement(confirmAcc).sendKeys(acc);

        driver.findElement(this.amount).clear();
        driver.findElement(this.amount).sendKeys(amountValue);
    }

    public void sendPayment() {
        driver.findElement(clickSend).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(successMessage).getText();
    }
    public String getErrorMessage() {
        try {
            WebElement errorElem = driver.findElement(errorMessage);
            return errorElem.isDisplayed() ? errorElem.getText() : "";
        } catch (Exception e) {
            return "";
        }
    }
}