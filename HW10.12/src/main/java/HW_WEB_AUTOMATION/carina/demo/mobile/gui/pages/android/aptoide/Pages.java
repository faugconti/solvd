package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;

public enum Pages {
    HOME("action_home"),
    SEARCH("action_search"),
    STORES("action_stores"),
    APPS("action_apps"),
    EDITORIAL("action_curation");

    private final String name;

    Pages(String name){
        this.name = name;
    }

    public String getNameId(){
        return this.name;
    }
}
