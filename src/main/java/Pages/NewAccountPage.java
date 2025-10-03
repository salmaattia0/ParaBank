package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NewAccountPage {
    private WebDriver driver;

    private By openAccountLink = By.xpath("//a[text()='Open New Account']");
    private By accountTypeDropdown = By.id("type");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By createButton = By.xpath("//input[@value='Open New Account']");

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToOpenNewAccount() {
        driver.findElement(openAccountLink).click();
    }

    public void selectAccountType(String type) {
        Select select = new Select(driver.findElement(accountTypeDropdown));
        select.selectByVisibleText(type);
    }

    public void selectFromAccount(String fromAccount) {
        Select select = new Select(driver.findElement(fromAccountDropdown));
        select.selectByVisibleText(fromAccount);
    }

    public void createAccount() {
        driver.findElement(createButton).click();
    }
}
