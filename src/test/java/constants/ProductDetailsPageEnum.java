package constants;

public enum ProductDetailsPageEnum {

    VALIDATE_PRICE_OF_ITEM1_PRODUCT_DETAILS("(//span[@class='a-price-whole'])[7]"),
    VALIDATE_PRICE_OF_ITEM2_PRODUCT_DETAILS("((//span[@class='a-offscreen'])[1]"),
    ADDTOCART_PRODUCT_DETAILS_BTN1("//span[@id='submit.add-to-cart']"),
    ADDTOCART_PRODUCT_DETAILS_BTN2("//span[@class='a-button a-spacing-small a-button-primary a-button-icon natc-enabled']"),
    ADDED_TO_CART_SUCESSFULLY("//h1[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']"),
    CLICK_ON_GOTO_CART_BTN("//span[@id='sw-gtc']"),
    CLICK_ON_PRODUCT2("//span[text()='Amazon Basics Wooden Shape Sorter and Rainbow Stacker…']"),

    ;

    public String desc;

    ProductDetailsPageEnum(String description) {
        this.desc = description;
    }

    public  String getLocator() {
        return desc;
    }
}

