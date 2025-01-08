package pages;

import baseTest.DriverFactory;
import baseTest.PropertyReader;
import constants.ShoppingCartPageEnum;
import utils.Utilities;

import java.io.IOException;

public class ShoppingCartPage extends DriverFactory{

    public void verifyTitleOfPage(String expectedValue) {
        Utilities.AssertTitle(expectedValue);
    }

    public void getSubTotalPriceOfProduct () throws IOException {

        String subTotalPrice = PropertyReader.getInstance().readProperty("subtotalPriceOfProduct");
        Utilities.validateProduct(ShoppingCartPageEnum.VALIDATE_SUBTOTAL_PRICE.getLocator(),subTotalPrice);
    }


}
