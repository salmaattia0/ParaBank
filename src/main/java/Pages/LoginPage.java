package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//input[@class='button']");
    private By invalidMessage = By.xpath("//p[contains(text(),'The username and password could not be verified.')]");
    private By emptyMessage = By.xpath("//p[contains(text(),'Please enter a username and password.')]");
    private By accountOverview = By.xpath("//h1[contains(text(),'Accounts Overview')]");
    private By logoutLink = By.linkText("Log Out");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
       wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField)
        );
        passwordInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        );
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
       wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        );
        loginBtn.click();
    }

    public String getInvalidMessageText() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement invalidMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(invalidMessage)
        );
        return invalidMsg.getText();
    }

    public String getEmptyMessageText() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emptyMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emptyMessage)
        );        return emptyMsg.getText();
    }

    public String getAccountOverviewMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountOverview)
        );        return successMsg.getText();
    }

    public void clickLogout() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(logoutLink)
        );
        logoutBtn.click();
    }
}
