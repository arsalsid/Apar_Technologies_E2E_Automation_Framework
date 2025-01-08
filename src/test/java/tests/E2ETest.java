package tests;

import baseTest.DriverFactory;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductDetailsPage;
import pages.ProductListingPage;
import pages.ShoppingCartPage;
import utils.Utilities;
import java.lang.reflect.Method;
import static utils.extentReports.ExtentTestManager.startTest;


public class E2ETest extends DriverFactory {

    LandingPage landingPage = new LandingPage();
    ProductListingPage productListingPage = new ProductListingPage();
    ProductDetailsPage productDetails = new ProductDetailsPage();

    ShoppingCartPage shoppingCart = new ShoppingCartPage();
    @Test(description = "Verify Title of landingPage", priority = 1)
    public void verifyTitleOfLandingPage(Method method) throws Exception {
        startTest(method.getName(), "Verify Title of landingPage");
        landingPage.verifyTitleOfPage("Amazon.com. Spend less. Smile more.");
        Utilities.takeScreenshot(driver,"LandingPage");
    }

    @Test(description = "User Enter the product item in a search bar", priority = 2)
    public void enterSearchItemOnLandingPage(Method method) throws Exception {
        startTest(method.getName(), "Enter Search Item on LandingPage");
        landingPage.searchProduct();
        Utilities.takeScreenshot(driver,"LandingPage");
    }

    @Test(description = "Verify Title of Product Page", priority = 3)
    public void verifyTitleOfProductPage(Method method) throws Exception {
        startTest(method.getName(), "Verify Title of ProductPage");
        productListingPage.verifyTitleOfProductPage("toys");
        Utilities.takeScreenshot(driver,"ProductPage");
    }

    @Test(description = "Select Products of Product Listing Page", priority = 4)
    public void selectProductsOfProductListingPage(Method method) throws Exception {
        startTest(method.getName(), "Select Products of ProductListingPage");
        productListingPage.selectProductItem();
        Utilities.takeScreenshot(driver,"ProductListingPage");
    }

    @Test(description = "Add to Cart Product Details Page", priority = 5)
    public void addToCartOnProductDetailsPage(Method method) throws Exception {
        startTest(method.getName(), "Add to Cart ProductDetailsPage");
        productDetails.AddToCartProduct();
        Utilities.takeScreenshot(driver,"ProductDetailsPage");
    }

    @Test(description = "Verify Title of Shopping Cart Page", priority = 6)
    public void verifyTitleOfShoppingCartPage(Method method) throws Exception {
        startTest(method.getName(), "Verify Title of Shopping Cart Page");
        shoppingCart.verifyTitleOfPage("Amazon.com Shopping Cart");
        Utilities.takeScreenshot(driver,"Shopping Cart Page");
    }
    @Test(description = "Get SubTotal Price Of Shopping Cart Product", priority = 7)
    public void getSubTotalPriceOfProduct(Method method) throws Exception {
        startTest(method.getName(), "Verify Subtotal Page of Shopping Cart Page");
        shoppingCart.getSubTotalPriceOfProduct();
        Utilities.takeScreenshot(driver,"Shopping Cart Page");
    }



}
