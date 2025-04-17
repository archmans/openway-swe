package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//li[@class='active']//a[normalize-space()='Shopping Cart']")
    WebElement shoppingCartText;

    @FindBy(xpath = "//button[@name='plus[25863745]']")
    WebElement plusButton;

    @FindBy(xpath = "//button[@name='minus[25863745]']")
    WebElement minusButton;

    @FindBy(xpath = "//li[2]//span[1]")
    WebElement price;

    @FindBy(xpath = "//input[@id='qty_25863745']")
    WebElement quantityField;

    @FindBy(xpath = "(//a[@class='btn btn-cart-remove'][normalize-space()='Remove'])[1]")
    WebElement removeButton;

    @FindBy(xpath = "//div[@class='success']")
    WebElement alertSuccess;

    @FindBy(xpath = "//div[@class='content']")
    WebElement emptyCartText;

    public Cart(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public boolean isShoppingCartTextVisible() {
        return wait.until(ExpectedConditions.visibilityOf(shoppingCartText)).isDisplayed();
    }

    public boolean isAlertRemovedVisible() {
        return wait.until(ExpectedConditions.visibilityOf(alertSuccess)).isDisplayed();
    }

    public boolean isPriceVisible() {
        return wait.until(ExpectedConditions.visibilityOf(price)).isDisplayed();
    }

    public boolean isQuantityFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOf(quantityField)).isDisplayed();
    }

    public int getCurrentQuantity() {
        String quantityText = wait.until(ExpectedConditions.visibilityOf(quantityField)).getDomProperty("value");
        assert quantityText != null;
        return Integer.parseInt(quantityText);
    }

    public void clickPlusButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        wait.until(ExpectedConditions.elementToBeClickable(plusButton)).click();
    }

    public void clickMinusButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        wait.until(ExpectedConditions.elementToBeClickable(minusButton)).click();
    }

    public void clickRemoveButtonFirstBook() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public void clickRemoveButtonSecondBook() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public boolean isEmptyCartTextVisible() {
        return wait.until(ExpectedConditions.visibilityOf(emptyCartText)).isDisplayed();
    }
}
