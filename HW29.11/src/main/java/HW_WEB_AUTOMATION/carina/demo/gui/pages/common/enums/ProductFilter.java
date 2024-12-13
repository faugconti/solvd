package HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums;

public enum ProductFilter {
    TYPE("Tipo de producto"),
    GENRE("Género"),
    CATEGORY("Categoría"),
    BRAND("Marca"),
    SIZE("Talle"),
    PRICE("Precio"),
    COLOUR("Color"),
    SUSTENTABLE("Sustentable"),
    CUSTOMIZABLE("Personalizable");

    private final String name;

    ProductFilter(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
