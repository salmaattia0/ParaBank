package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferPage {
    private WebDriver driver;
    private By amountField = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.cssSelector("input[value='Transfer']");
    private By confirmationMessage = By.id("transferConfirmation");

    public TransferPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterAmount(String amount) {
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);
    }

    public void selectFromAccount(String fromAccount) {
        driver.findElement(fromAccountDropdown).sendKeys(fromAccount);
    }

    public void selectToAccount(String toAccount) {
        driver.findElement(toAccountDropdown).sendKeys(toAccount);
    }

    public void clickTransfer() {
        driver.findElement(transferButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}
