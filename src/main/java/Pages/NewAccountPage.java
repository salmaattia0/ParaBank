package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NewAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private By accountLink = By.xpath("//a[text()='Open New Account']");
    private By accountTypeDropdown = By.id("type");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By createButton = By.xpath("//input[@value='Open New Account']");
    private By successMsg = By.xpath("//h1[text()='Account Opened!']");
    private By accountsOverviewLink = By.xpath("//a[text()='Accounts Overview']");
    private By accountNumberLink = By.xpath("//a[contains(@href, 'activity.htm?id=')]");
    private By balanceValue = By.id("balance");
    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToOpenNewAccount() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement openAccountLink = wait.until(
                ExpectedConditions.elementToBeClickable(accountLink)
        );
        openAccountLink.click();
    }

    public void selectAccountType(String type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(accountTypeDropdown)
        );
        Select select = new Select(dropdown);
        select.selectByVisibleText(type);
    }

    public void selectFromAccount(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccountDropdown)
        );
        Select select = new Select(dropdown);
        wait.until(driver1 -> select.getOptions().size() > index);
        select.selectByIndex(index);
    }

    public void createAccount() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement createNewAccount = wait.until(
                ExpectedConditions.elementToBeClickable(createButton)
        );
        createNewAccount.click();
    }
    public String getSuccessText() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Msg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMsg)
        );
        return Msg.getText();
    }
    public String getNewAccountNumber() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accountNum = wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountNumberLink)
        );
        return accountNum.getText();
    }
    public void navigateToAccountsOverview() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement overviewLink = wait.until(
                ExpectedConditions.elementToBeClickable(accountsOverviewLink)
        );
        overviewLink.click();
    }
    public boolean isAccountDisplayed(String accountNumber) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> accounts = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table//a"))
        );
        for (WebElement acc : accounts) {
            if (acc.getText().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
    public void openAccountDetails(String accountNumber) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accountLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + accountNumber + "']"))
        );
        accountLink.click();
    }
    public String getDisplayedAccountType() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountType")));
        wait.until(driver1 -> !typeElement.getText().trim().isEmpty());
        return typeElement.getText().trim();
    }

    public String getDisplayedBalance() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement balance = wait.until(
                ExpectedConditions.visibilityOfElementLocated(balanceValue)
        );
        return balance.getText();
    }
}
