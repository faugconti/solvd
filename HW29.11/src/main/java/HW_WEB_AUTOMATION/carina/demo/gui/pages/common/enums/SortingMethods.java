package HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums;

public enum SortingMethods {

    MOST_SOLD("most-sold"),
    MOST_RELEVANT("most-relevant"),
    NEWEST_PRODUCT("newest-products"),
    LOWEST_PRICE("lowest-price"),
    HIGHEST_PRICE("highest-price"),
    PRODUCT_DISCOUNT("product-discount");

    private final String name;

    SortingMethods(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
