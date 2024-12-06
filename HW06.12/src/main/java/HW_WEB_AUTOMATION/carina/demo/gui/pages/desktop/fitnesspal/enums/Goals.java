package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums;

public enum Goals {
    LOSE_WEIGHT("Lose weight"),
    MAINTAIN_WEIGHT("Maintain weight"),
    GAIN_WEIGHT("Gain weight"),
    GAIN_MUSCLE("Gain muscle"),
    MODIFY_DIET("Modify my diet"),
    MANAGE_STRESS("Manage stress"),
    INCREASE_STEP_COUNT("Increase step count");

    private final String originalText;
    private static final int maxAmount = 3;

    private Goals(String originalText){
        this.originalText = originalText;
    }

    public String getOriginalText(){
        return originalText;
    }
}
