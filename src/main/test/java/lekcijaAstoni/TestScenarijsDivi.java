package lekcijaAstoni;

import lekcijaAstoni.pageObjects.CartPage;
import lekcijaAstoni.pageObjects.CheckoutPage;
import lekcijaAstoni.pageObjects.LoginPage;
import lekcijaAstoni.pageObjects.ProductsPage;
import lekcijaAstoni.pageObjects.BasePage;
import lekcijaSeptini.labDarbs.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScenarijsDivi extends BaseTest {

    @Test
    public void testCheckoutPageMandatoryFields() throws InterruptedException {
        System.out.println("1. Navigēt uz saiti https://www.saucedemo.com/");
        System.out.println("2. Ielogoties ar pareizu lietotāja vārdu/paroli");
        LoginPage loginPage = new LoginPage(parluks);
        ProductsPage produktuLapa = new ProductsPage(parluks);
        loginPage.login("standard_user","secret_sauce");
        Assert.assertEquals(produktuLapa.getPageTitle().getText(), "PRODUCTS");

        System.out.println("3. Doties uz grozu");
        CartPage grozaLapa = new CartPage(parluks);
        produktuLapa.getCartButton().click();
        Assert.assertEquals(grozaLapa.getPageTitle().getText(),"YOUR CART");

        System.out.println("4. Doties uz Checkout");
        grozaLapa.getCheckoutButton().click();

        System.out.println("5. Pārbaudīt, ka FirstName/LastName/Zip code ir obligāts");
        CheckoutPage checkoutLapa = new CheckoutPage(parluks);
        Assert.assertEquals(checkoutLapa.getPageTitle().getText(),"CHECKOUT: YOUR INFORMATION");
        checkoutLapa.clickContinueButton();

        Assert.assertEquals(checkoutLapa.getErrorText(),"Error: First Name is required");

        checkoutLapa.inputFirstName("Juris");
        checkoutLapa.clickContinueButton();
        Assert.assertEquals(checkoutLapa.getErrorText(),"Error: Last Name is required");

    }

}
