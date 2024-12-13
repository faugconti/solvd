package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.ActivityLevels;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Genre;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Goals;

import java.time.LocalDate;
import java.util.*;


public class User {

    private float height_cm;
    private float height_in;
    private float weight_kg;
    private float weight_lb;
    private String firstName;
    private String country;
    private Genre genre;
    private LocalDate birthDate;
    private List<Goals> goals;
    private Map<Goals, Set<String>> goalOptions;
    private ActivityLevels activityLevel;

    public User(){
        this.goals = new ArrayList<>();
        this.goalOptions = new LinkedHashMap<>();
    }

    public float getHeight_cm() {
        return height_cm;
    }

    public float getHeight_in() {
        return height_in;
    }

    public float getWeight_kg() {
        return weight_kg;
    }

    public float getWeight_lb() {
        return weight_lb;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCountry() {
        return country;
    }

    public List<Goals> getGoals() {
        return goals;
    }

    public String getFirstName() {
        return firstName;
    }

    public ActivityLevels getActivityLevel() {
        return activityLevel;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setActivityLevel(ActivityLevels activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGoals(List<Goals> goals) {
        this.goals = goals;
    }

    public void setHeight_cm(float height_cm) {
        this.height_cm = height_cm;
    }

    public void setHeight_in(float height_in) {
        this.height_in = height_in;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setWeight_kg(float weight_kg) {
        this.weight_kg = weight_kg;
    }

    public void setWeight_lb(float weight_lb) {
        this.weight_lb = weight_lb;
    }

    public void setGoalOptions(Map<Goals, Set<String>> goalOptions) {
        this.goalOptions = goalOptions;
    }

    public void addGoalOption(Goals goal, Set<String> options) {
        this.goalOptions.put(goal, options);
    }

    public Map<Goals, Set<String>> getGoalOptions() {
        return goalOptions;
    }
}
