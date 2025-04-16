package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TryTest {
    WebDriver driver;
    String url;

    @Parameters({"url"})
    @BeforeTest
    public void setUp(String url) {
        driver = new ChromeDriver();
        this.url = url;
    }

    @Test
    public void testOpen() {
        driver.get(url);
        System.out.println("The page tittle is " + driver.getTitle());
        driver.quit();
    }
}