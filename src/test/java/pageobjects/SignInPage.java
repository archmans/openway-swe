package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//span[@id='nav-signin-text']")
    WebElement signInButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        PageFactory.initElements(driver, this);
    }

    public boolean isSignInButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }
}