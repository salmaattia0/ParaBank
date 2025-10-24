package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By transferMoneyLink = By.xpath("//a[text()='Transfer Funds']");
    private By amountField = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.cssSelector("input[value='Transfer']");
    private By confirmationMessage = By.xpath("//h1[text()='Transfer Complete!']");

    public TransferPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToTransferPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement transferLink = wait.until(
                ExpectedConditions.elementToBeClickable(transferMoneyLink)
        );
        transferLink.click();
    }

    public void enterAmount(String amount) {
        driver.findElement(amountField).clear();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement transferAmount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField)
        );
        transferAmount.sendKeys(amount);
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

    public void selectToAccount(int toAccount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toAccountDropdown)
        );
        Select select = new Select(dropdown);
        wait.until(driver1 -> select.getOptions().size() > toAccount);
        select.selectByIndex(toAccount);
    }

    public void clickTransfer() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickOnTransferButton = wait.until(
                ExpectedConditions.elementToBeClickable(transferButton)
        );
        clickOnTransferButton.click();
    }

    public String getConfirmationMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Msg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(confirmationMessage)
        );
        return Msg.getText();
    }
}
