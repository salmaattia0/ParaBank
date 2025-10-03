package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BillPage {

    private WebDriver driver;

    private By billPayLink = By.linkText("Bill Pay");
    private By payeeNameField = By.id("payeeName");
    private By addressField = By.id("address");
    private By accountNumberField = By.id("account");
    private By amountField = By.id("amount");
    private By sendPaymentButton = By.cssSelector("input[value='Send Payment']");
    private By confirmationMessage = By.id("paymentConfirmation");
    private By errorMessage = By.cssSelector(".error"); // generic error message selector
    private By accountsOverviewLink = By.linkText("Accounts Overview");
    private By transactionsTable = By.cssSelector(".transactionsTable tr td.amount");

    public BillPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToBillPay() {
        driver.findElement(billPayLink).click();
    }

    public void enterPayeeDetails(String name, String address, String account) {
        driver.findElement(payeeNameField).clear();
        driver.findElement(payeeNameField).sendKeys(name);

        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);

        driver.findElement(accountNumberField).clear();
        driver.findElement(accountNumberField).sendKeys(account);
    }

    public void enterAmount(String amount) {
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);
    }

    public void sendPayment() {
        driver.findElement(sendPaymentButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public String getErrorMessage() {
        WebElement errorElem = driver.findElement(errorMessage);
        return errorElem.isDisplayed() ? errorElem.getText() : "";
    }

    public void navigateToAccountsOverview() {
        driver.findElement(accountsOverviewLink).click();
    }

    public boolean isTransactionRecorded(String amount) {
        List<WebElement> transactions = driver.findElements(transactionsTable);
        for (WebElement t : transactions) {
            if (t.getText().trim().equals(amount)) {
                return true;
            }
        }
        return false;
    }
}
