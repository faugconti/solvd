package HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums;

public enum SubFilters {

    BOOTS(ProductFilter.TYPE,"Botas"),
    CLEATS(ProductFilter.TYPE,"Botines"),
    FLOPS(ProductFilter.TYPE,"Ojotas"),
    SANDALS(ProductFilter.TYPE,"Sandalias"),
    SNICKERS(ProductFilter.TYPE,"Zapatillas"),
    CLOGS(ProductFilter.TYPE,"Zuecos"),
    MALE(ProductFilter.GENRE,"Hombre"),
    FEMALE(ProductFilter.GENRE,"Mujer"),
    UNISEX(ProductFilter.GENRE,"Unisex"),
    BASQUET(ProductFilter.CATEGORY,"Basquet"),
    SOCCER(ProductFilter.CATEGORY,"Futbol"),
    HOCKEY(ProductFilter.CATEGORY,"Hockey"),
    KIDS(ProductFilter.CATEGORY,"Kids"),
    FASHION(ProductFilter.CATEGORY,"Moda"),
    ADIDAS(ProductFilter.BRAND,"adidas"),
    CONVERSE(ProductFilter.BRAND,"Converse"),
    KAPPA(ProductFilter.BRAND,"Kappa"),
    NIKE(ProductFilter.BRAND,"Nike"),
    TWENTY_FIVE(ProductFilter.SIZE,"25"),
    THIRTY_SIX(ProductFilter.SIZE,"36");





    private ProductFilter filterName;
    private String subFilterName;

    SubFilters(ProductFilter filter, String name){
        this.filterName = filter;
        this.subFilterName = name;
    }

    public String getName(){
        return this.subFilterName;
    }

    public ProductFilter getFilterName(){
        return this.filterName;
    }

}
