package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.ActivityLevels;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Genre;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Goals;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AccountCreationBase extends AbstractPage {
    public AccountCreationBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
    }

    public abstract void insertName(String name);

    public abstract void pickGoals(List<Goals> accountGoals);

    public abstract void processGoals(Map<Goals, Set<String>> accountGoals);

    public abstract void processActivityLevel(ActivityLevels activityLevel);

    public abstract void manageDemographic(Genre genre, String country, String birthDate);

    public abstract void manageBodyData(float height, float weight, float goalWeight);

    public abstract void proceedWeightGoal(String goal);

    public abstract void submitCredentials(String email, String password);

    public abstract void createUsername(String username);
}
