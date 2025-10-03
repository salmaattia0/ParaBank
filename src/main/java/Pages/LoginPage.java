package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//input[@class='button']");
    private By invalidMessage = By.xpath("//p[contains(text(),'The username and password could not be verified.')]");
    private By emptyMessage = By.xpath("//p[contains(text(),'Please enter a username and password.')]");
    private By logoutLink = By.linkText("Log Out");
    private By accountOverview = By.xpath("//h1[contains(text(),'Accounts Overview')]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getInvalidMessageText() {
        return driver.findElement(invalidMessage).getText();
    }

    public String getEmptyMessageText() {
        return driver.findElement(emptyMessage).getText();
    }

    public String isAtAccountsOverview() {
        return driver.findElement(accountOverview).getText();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }
}
