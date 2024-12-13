package HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums;

public enum Menus {
    CATEGORIES("categorias"),
    MALE("hombre"),
    FEMALE("mujer"),
    INFANT("infantil"),
    SHOES("zapatilas"),
    BRANDS("marcas"),
    NEW("lanzamiento");

    private final String name;

    Menus(String name) {
        this.name = name;
    }

    public String getID(){
        return this.name;
    }
}


