package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums;

public enum GoalsLoseWeight {
    LACK_OF_TIME("Lack of time"),
    HARD_REGIMEN("The regimen was too hard to follow"),
    DID_NOT_ENJOY_FOOD("Did not enjoy the food"),
    DIFFICULTY_EATING_FOOD("Difficult to make food choices"),
    SOCIAL_EATING("Social eating and events"),
    FOOD_CRAVINGS("Food cravings"),
    LACK_OF_PROGRESS("Lack of progress"),
    LOSE_QUARTER("Lose 0.25 kilograms per week"),
    LOSE_HALF("Lose 0.5 kilograms per week (Recommended)"),
    LOSE_3QUARTERS("Lose 0.75 kilograms per week"),
    LOSE_KILOGRAM("Lose 1 kilogram per week");

    private final String originalText;
    private static final int maxAmount = 3;
    private static final String descriptor = "lose-weight";


    private GoalsLoseWeight(String originalText){
        this.originalText = originalText;
    }

    public String getOriginalText(){
        return originalText;
    }
}
