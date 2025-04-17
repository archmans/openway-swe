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
    String bookTitle2;

    NavBar navBar;
    SignIn signIn;
    SearchProduct searchProduct;
    Product product;
    Cart cart;

    @Parameters({"url", "email", "password", "bookTitle1", "bookTitle2"})
    @BeforeTest
    public void setUp(String url, String email, String password, String bookTitle1, String bookTitle2) {
        driver = new ChromeDriver();

        this.url = url;
        this.email = email;
        this.password = password;
        this.bookTitle1 = bookTitle1;
        this.bookTitle2 = bookTitle2;

        driver.get(url);
        navBar = new NavBar(driver);
        signIn = new SignIn(driver);
        searchProduct = new SearchProduct(driver);
        product = new Product(driver);
        cart = new Cart(driver);
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

    @Test(priority = 12)
    public void tc12_CartIconVisible() {
        boolean isVisible = navBar.isCartIconVisible();
        product.closeModal();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 13)
    public void tc13_ClickCartIcon() {
        navBar.clickCartIcon();
        boolean isCartPageVisible = cart.isShoppingCartTextVisible();
        Assert.assertTrue(isCartPageVisible);
    }

    @Test(priority = 14)
    public void tc14_QuantityFieldVisible() {
        boolean isVisible = cart.isQuantityFieldVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 15)
    public void tc15_AddQuantity() {
        int currentQuantity = cart.getCurrentQuantity();
        int expectedQuantity = currentQuantity + 1;
        cart.clickPlusButton();
        int actualQuantity = cart.getCurrentQuantity();
        Assert.assertEquals(actualQuantity, expectedQuantity);
    }

    @Test(priority = 16)
    public void tc16_SubtractQuantity() {
        int currentQuantity = cart.getCurrentQuantity();
        int expectedQuantity = currentQuantity - 1;
        cart.clickMinusButton();
        int actualQuantity = cart.getCurrentQuantity();
        Assert.assertEquals(actualQuantity, expectedQuantity);
    }

    @Test(priority = 17)
    public void tc17_PriceVisible() {
        boolean isVisible = cart.isPriceVisible();
        Assert.assertTrue(isVisible);
    }

    @Test(priority = 18)
    public void tc18_AddAnotherBookToCart() {
        navBar.searchByBookTitle(bookTitle2);
        searchProduct.clickProduct(bookTitle2);
        product.clickAddToCartButton();
        boolean isModalVisible = product.isModalTextVisible();
        Assert.assertTrue(isModalVisible);
        product.closeModal();
        navBar.clickCartIcon();
        boolean isCartPageVisible = cart.isShoppingCartTextVisible();
        Assert.assertTrue(isCartPageVisible);
    }

    @Test(priority = 19)
    public void tc19_RemoveFirstBookFromCart() {
        cart.clickRemoveButtonFirstBook();
        boolean isFirstBookRemoved = cart.isAlertRemovedVisible();
        Assert.assertTrue(isFirstBookRemoved);
    }

    @Test(priority = 20)
    public void tc20_RemoveSecondBookFromCart() {
        cart.clickRemoveButtonSecondBook();
        boolean isEmptyCartTextVisible = cart.isEmptyCartTextVisible();
        Assert.assertTrue(isEmptyCartTextVisible);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}