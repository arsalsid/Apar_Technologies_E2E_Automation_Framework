package pages;

import baseTest.DriverFactory;
import baseTest.PropertyReader;
import constants.ProductListingPageEnum;
import utils.Utilities;

import java.io.IOException;

public class ProductListingPage extends DriverFactory {

    public void verifyTitleOfProductPage(String expectedValue) {
        Utilities.AssertTitle(expectedValue);
    }

    public void selectProductItem() throws IOException {
        String item1 = PropertyReader.getInstance().readProperty("product1");
        String getExpectedResult = PropertyReader.getInstance().readProperty("product1ExpectedPrice");
        Utilities.validateText(ProductListingPageEnum.VALIDATE_PRICE_OF_ITEM1.getLocator(), getExpectedResult);
        System.out.println("Product Item Name: " +item1);
        Utilities.toBeClickOnButton(ProductListingPageEnum.CLICK_ON_ITEM1.getLocator());
    }
}
