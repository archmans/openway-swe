package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Product {
    WebDriver driver;
    WebDriverWait wait;

    WebElement productTitle;

    @FindBy(xpath = "//button[normalize-space()='Add to Cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='modal-text']")
    WebElement modalText;

    @FindBy(xpath = "//i[@class='ti-close']")
    WebElement closeButton;

    public Product(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle(String bookTitle) {
        productTitle = driver.findElement(By.xpath("//h2[contains(text(),'" + bookTitle + "')]"));
        return productTitle.getText();
    }

    public boolean isAddToCartButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(addToCartButton)).isDisplayed();
    }

    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isModalTextVisible() {
        return wait.until(ExpectedConditions.visibilityOf(modalText)).isDisplayed();
    }

    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
}
