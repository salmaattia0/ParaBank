package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewAccountPage {
    private WebDriver driver;

    private By accountTypeDropdown = By.id("type");
    private By fundingAccountDropdown = By.id("fromAccountId");
    private By openNewAccountButton = By.cssSelector("input[value='Open New Account']");
    private By newAccountId = By.id("newAccountId");
    private By accountsOverviewLink = By.linkText("Accounts Overview");
    private By errorMessage = By.cssSelector(".error, .title + p");

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectAccountType(String type) {
        WebElement dropdown = driver.findElement(accountTypeDropdown);
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(type);
        } catch (Exception e) {
            dropdown.sendKeys(type);
        }
    }

    public void selectFundingAccount(String accountId) {
        WebElement dropdown = driver.findElement(fundingAccountDropdown);
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(accountId);
        } catch (Exception e) {
            dropdown.sendKeys(accountId);
        }
    }

    public void clickOpenNewAccount() {
        driver.findElement(openNewAccountButton).click();
    }

    public String getNewAccountId() {
        try {
            return driver.findElement(newAccountId).getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    public void goToAccountsOverview() {
        driver.findElement(accountsOverviewLink).click();
    }

    public boolean isAccountPresentInOverview(String accountId) {
        return driver.getPageSource().contains(accountId);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
