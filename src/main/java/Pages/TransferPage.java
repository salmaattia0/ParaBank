package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferPage {
    private WebDriver driver;

    private By transferMoneyLink = By.xpath("//a[text()='Transfer Funds']");
    private By amountField = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.cssSelector("input[value='Transfer']");
    private By confirmationMessage = By.id("transferConfirmation");

    public TransferPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToTransferPage() {
        driver.findElement(transferMoneyLink).click();
    }

    public void enterAmount(String amount) {
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);
    }

    public void selectFromAccount(String fromAccount) {
        Select select = new Select(driver.findElement(fromAccountDropdown));
        select.selectByVisibleText(fromAccount);
    }

    public void selectToAccount(String toAccount) {
        Select select = new Select(driver.findElement(toAccountDropdown));
        select.selectByVisibleText(toAccount);
    }

    public void clickTransfer() {
        driver.findElement(transferButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}
