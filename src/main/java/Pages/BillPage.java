package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillPage {

    private WebDriver driver;
    private WebDriverWait wait;

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
    private By fromAccountDropdown = By.xpath("//select[@name='fromAccountId']");
    private By clickSend = By.xpath("//input[@value='Send Payment']");
    private By successMessage = By.xpath("//h1[text()='Bill Payment Complete']");

    public BillPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToBillPay() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement billPay = wait.until(
                ExpectedConditions.elementToBeClickable(billPayLink)
        );
        billPay.click();
    }

    public void enterPayeeDetails(String name, String address, String city, String state,
                                  String zip, String phone, String acc) {
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
    }

    public void selectFromAccount(int fromAccount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccountDropdown)
        );
        Select select = new Select(dropdown);
        wait.until(driver1 -> select.getOptions().size() > fromAccount);
        select.selectByIndex(fromAccount);
    }
    public void enterAmount(String amt) {
        WebElement amountField = driver.findElement(amount);
        amountField.clear();
        amountField.sendKeys(amt);
    }

    public void sendPayment() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickOnSendButton = wait.until(
                ExpectedConditions.elementToBeClickable(clickSend)
        );
        clickOnSendButton.click();
    }

    public String getConfirmationMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Msg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        );
        return Msg.getText();
    }
}