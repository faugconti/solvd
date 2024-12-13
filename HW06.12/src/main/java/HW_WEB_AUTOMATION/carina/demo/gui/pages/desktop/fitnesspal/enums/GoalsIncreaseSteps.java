package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums;

public enum GoalsIncreaseSteps {
    LESS_THAN_1000("Less than 1,000"),
    _1000_TO_3000("1,000 to 3,000"),
    _3000_TO_7000("3,000 to 7,000"),
    MORE_THAN_7000("More than 7,000"),
    UNKNOWN("I don’t know");

    private final String originalText;
    private static final int maxAmount = 3;
    private static final String descriptor = "increase-steps";


    private GoalsIncreaseSteps(String originalText){
        this.originalText = originalText;
    }

    public String getOriginalText(){
        return originalText;
    }
}
