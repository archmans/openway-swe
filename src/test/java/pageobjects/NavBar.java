package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavBar {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@id='nav-signin-text']")
    WebElement signInButton;

    @FindBy(xpath = "//div[@class='search-bar']//input[@id='filter_name']")
    WebElement searchField;

    @FindBy(xpath = "//div[@id='show-your-cart']//i[@class='ti-bag']")
    WebElement cartIcon;

    public NavBar(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public boolean isSignInButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public boolean isSearchFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOf(searchField)).isDisplayed();
    }

    public void searchByBookTitle(String bookTitle) {
        searchField.clear();
        wait.until(ExpectedConditions.elementToBeClickable(searchField)).sendKeys(bookTitle);
        searchField.sendKeys(Keys.RETURN);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
    }

    public boolean isCartIconVisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        return wait.until(ExpectedConditions.visibilityOf(cartIcon)).isDisplayed();
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
}