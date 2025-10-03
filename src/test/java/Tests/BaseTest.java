package Tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    private static int i = 1;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File directory = new File("./screenshots/");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "./screenshots/" + result.getName() + "_" + i + ".png";
                FileHandler.copy(src, new File(screenshotPath));
                i++;
                System.out.println("Screenshot saved: " + screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
