package constants;

public enum ProductListingPageEnum {

    CLICK_ON_ITEM1("//span[text()='Fisher-Price Musical Toy, Rockin’ Record Player for Preschool Pretend Play for Kids Ages 3+ Year']"),
    VALIDATE_PRICE_OF_ITEM1("//span[@class='a-color-base' and text()= '$26.73 delivery']"),
    ;

    public String desc;

    ProductListingPageEnum(String description) {
        this.desc = description;
    }

    public  String getLocator() {
        return desc;
    }
}

