package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import HW_WEB_AUTOMATION.carina.demo.gui.components.CreationForm;
import HW_WEB_AUTOMATION.carina.demo.gui.components.CreationModal;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.ActivityLevels;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Genre;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Goals;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = AccountCreationBase.class)
public class AccountCreation extends AccountCreationBase{


    @FindBy(xpath = "//main[contains(@class, 'MuiBox-root')]")
    private CreationModal mainContainer;

    @FindBy(xpath = "//form[contains(@class,'MuiBox-root')]")
    private CreationForm creationForm;

    public AccountCreation(WebDriver driver) {
        super(driver);
        setPageURL("account/create/input-name");
    }

    @Override
    public void insertName(String name){
        mainContainer.writeInput(name);
        mainContainer.clickNext();
    }

    @Override
    public void pickGoals(List<Goals> accountGoals){
        Set<String> goalTexts = accountGoals.stream()
                .map(Goals::getOriginalText)
                .collect(Collectors.toSet());
        mainContainer.manageOptions(goalTexts);
        mainContainer.clickNext();

    }
    @Override
    public void processGoals(Map<Goals,Set<String>> goalsOptions) {
        for(Set<String> options : goalsOptions.values()){
            mainContainer.clickNext();
            mainContainer.manageOptions(options);
            mainContainer.clickNext();
        }
    }

    @Override
    public void processActivityLevel(ActivityLevels activityLevels){
        mainContainer.clickNext();
        mainContainer.manageOptions(new HashSet<String>(Collections.singleton(activityLevels.getOriginalText())));
        mainContainer.clickNext();
    }

    @Override
    public void manageDemographic(Genre genre, String country, String birthDate){
        creationForm.chooseGenre(genre);
        creationForm.chooseDate(birthDate);
        creationForm.chooseCountry(country);
        mainContainer.clickNext();
    }
    @Override
    public void manageBodyData(float height, float weight, float goalWeight){
        creationForm.setCurrentWeight(weight);
        creationForm.setGoalWeightInput(goalWeight);
        creationForm.setHeight(height);
        mainContainer.clickNext();
    }

    @Override
    public void proceedWeightGoal(String goal){
        mainContainer.manageOptions(new HashSet<String>(Collections.singleton(goal)));
        mainContainer.clickNext();
    }
    @Override
    public void submitCredentials(String email, String password){
        creationForm.setEmail(email);
        creationForm.setPassword(password);
        creationForm.acceptTerms();
        mainContainer.clickNext();
    }
    @Override
    public void createUsername(String username){
        creationForm.setUsername(username);
        mainContainer.clickNext();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



}
