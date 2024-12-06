package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums;

public enum GoalsGainMuscle {
    TONE_UP("Tone up – you want visible muscles with as little mass as possible"),
    BULK_UP("Bulk up – you want large, well-defined muscles, with a low percentage of body fat"),
    GET_STRONG("Get strong – you want to lift the maximum amount of weight and are not concerned with body fat or muscle definition");

    private final String originalText;
    private static final int maxAmount = 3;
    private static final String descriptor = "gain-muscle";

    private GoalsGainMuscle(String originalText){
        this.originalText = originalText;
    }

    public String getOriginalText(){
        return originalText;
    }
}
