package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.*;

import java.time.Duration;

public class ShoppingCartTest {
    WebDriver driver;
    WebDriverWait wait;
    String url;
    SignInPage signInPage;

    @Parameters({"url"})
    @BeforeTest
    public void setUp(String url) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        this.url = url;

        driver.get(url);
        signInPage = new SignInPage(driver);
    }

    @Test(priority = 1)
    public void tc01_VisibilitySignInButton() {
        boolean isVisible = signInPage.isSignInButtonVisible();
        Assert.assertTrue(isVisible);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}