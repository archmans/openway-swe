package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchProduct {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='keyword']")
    WebElement keywordField;

    WebElement product;

    public SearchProduct(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public String getKeywordFieldValue() {
        return wait.until(ExpectedConditions.visibilityOf(keywordField)).getDomProperty("value");
    }

    public void clickProduct(String bookTitle) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        product = driver.findElement(By.xpath("//a[contains(text(),'" + bookTitle + "')]"));
        wait.until(ExpectedConditions.elementToBeClickable(product)).click();
    }
}
