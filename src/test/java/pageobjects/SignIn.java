package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignIn {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@class='signin_main']")
    WebElement signInHeader;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='ps']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='button-login']")
    WebElement loginButton;

    @FindBy(xpath = "//h3[@class='title title-categories']")
    WebElement accountPageHeader;

    public SignIn(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public boolean isSignInPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(signInHeader)).isDisplayed();
    }

    public boolean isEmailFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOf(emailField)).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOf(passwordField)).isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
    }

    public void inputEmail(String email) {
        emailField.clear();
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
    }
    public void inputPassword(String password) {
        passwordField.clear();
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isAccountPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(accountPageHeader)).isDisplayed();
    }
}
