package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.*;

public class ShoppingCartTest {
    WebDriver driver;
    String url;
    String email;
    String password;
    String bookTitle1;
    NavBar navBar;
    SignIn signIn;
    SearchProduct searchProduct;
    Product product;

    @Parameters({"url", "email", "password", "bookTitle1"})
    @BeforeTest
    public void setUp(String url, String email, String password, String bookTitle1) {
        driver = new ChromeDriver();

        this.url = url;
        this.email = email;
        this.password = password;
        this.bookTitle1 = bookTitle1;

        driver.get(url);
        navBar = new NavBar(driver);
        signIn = new SignIn(driver);
        searchProduct = new SearchProduct(driver);
        product = new Product(driver);
    }

    @Test(priority = 1)
    public void tc01_SignInButtonVisible() {
        boolean isVisible = navBar.isSignInButtonVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 2)
    public void tc02_OpenSignInPage() {
        navBar.clickSignInButton();
        Assert.assertTrue(signIn.isSignInPageDisplayed());
    }

    @Test(priority = 3)
    public void tc03_EmailFieldVisible() {
        boolean isVisible = signIn.isEmailFieldVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 4)
    public void tc04_PasswordFieldVisible() {
        boolean isVisible = signIn.isPasswordFieldVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 5)
    public void tc05_LoginButtonVisible() {
        boolean isVisible = signIn.isLoginButtonVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 6)
    public void tc06_Login() {
        signIn.inputEmail(email);
        signIn.inputPassword(password);
        signIn.clickLoginButton();
        Assert.assertTrue(signIn.isAccountPageDisplayed());
    }

    @Test(priority = 7)
    public void tc07_SearchFieldVisible() {
        boolean isVisible = navBar.isSearchFieldVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 8)
    public void tc08_SearchBookByTitle() {
        navBar.searchByBookTitle(bookTitle1);
        String keywordValue = searchProduct.getKeywordFieldValue();
        Assert.assertEquals(keywordValue, bookTitle1);
    }

    @Test(priority = 9)
    public void tc09_ClickProduct() {
        searchProduct.clickProduct(bookTitle1);
        String productTitle = product.getProductTitle(bookTitle1);
        Assert.assertEquals(productTitle, bookTitle1);
    }

    @Test(priority = 10)
    public void tc10_AddToCartButtonVisible() {
        boolean isVisible = product.isAddToCartButtonVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 11)
    public void tc11_AddToCart() {
        product.clickAddToCartButton();
        boolean isModalVisible = product.isModalTextVisible();
        Assert.assertTrue(isModalVisible);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}