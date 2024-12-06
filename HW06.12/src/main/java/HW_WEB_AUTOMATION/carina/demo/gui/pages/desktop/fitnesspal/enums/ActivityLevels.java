package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums;

public enum ActivityLevels {
    NOT_VERY_ACTIVE("Not Very Active"),
    LIGHTLY_ACTIVE("Lightly Active"),
    ACTIVE("Active"),
    VERY_ACTIVE("Very Active");

    private final String originalText;
    private static final int maxAmount = 1;
    private static final String descriptor = "activity-level";

    private ActivityLevels(String originalText){
        this.originalText = originalText;
    }

    public String getOriginalText(){
        return originalText;
    }
}
