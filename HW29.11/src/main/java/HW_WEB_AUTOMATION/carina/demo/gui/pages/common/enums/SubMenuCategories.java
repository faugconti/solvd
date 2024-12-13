package HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums;

public enum SubMenuCategories {

    //MALE MENU

    MEN_SHOES("hombre-calzado","/hombre/calzado",Menus.MALE),
    SHOES("hombre-calzado-zapatillas","/hombre/calzado/zapatillas",Menus.MALE),
    BOOTS("hombre-calzado-botas","/hombre/calzado/botas",Menus.MALE),
    FLOPS("hombre-calzado-ojotas","/hombre/calzado/ojotas",Menus.MALE),
    MEN_INDUMENTARY("hombre-indumentaria","/hombre/indumentaria",Menus.MALE),
    SHORTS("hombre-indumentaria-shorts","/hombre/indumentaria/shorts",Menus.MALE),
    PANTS("hombre-indumentaria-pantalones","/hombre/indumentaria/pantalones",Menus.MALE),

    //CATEGORIES MENU

    RUNNING("categorias-running","/categorias/running",Menus.CATEGORIES),
    TRAINING("categorias-training","/categorias/training",Menus.CATEGORIES),
    MOTORSPORT("categorias-motorsport","/categorias/motorsport",Menus.CATEGORIES),
    OUTDOOR("categorias-outdoor","/categorias/outdoor",Menus.CATEGORIES),
    VOLEY("categorias-voley","/categorias/voley",Menus.CATEGORIES);

    private final String name;
    private final String path;
    private final Menus menuType;

    SubMenuCategories(String name, String path, Menus menuType) {
        this.name = name;
        this.path = path;
        this.menuType = menuType;
    }

    public String getID(){
        return this.name;
    }

    public String getUrl(){
        return this.path;
    }

    public Menus getMenu(){
        return this.menuType;
    }



}
