package HW_WEB_AUTOMATION.carina.demo;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.*;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;


public class FitnessPal implements IAbstractTest {
    @Test
    @MethodOwner(owner = "Faconti")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testSignUp() throws IOException {


        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/test/resources/_user.properties")));

        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        String username = properties.getProperty("username");

        User account = new User();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        account.setFirstName("Jonas");
        account.setGoals(Arrays.asList(Goals.LOSE_WEIGHT,Goals.GAIN_MUSCLE));
        account.addGoalOption(Goals.LOSE_WEIGHT,
                new HashSet<>(Arrays.asList(
                        GoalsLoseWeight.LACK_OF_TIME.getOriginalText(),
                        GoalsLoseWeight.HARD_REGIMEN.getOriginalText()
                )));
        account.addGoalOption(Goals.GAIN_MUSCLE,
                new HashSet<>(Arrays.asList(
                        GoalsGainMuscle.BULK_UP.getOriginalText(),
                        GoalsGainMuscle.GET_STRONG.getOriginalText()
                )));

        account.setWeight_kg(72.F);
        account.setHeight_cm(178F);
        account.setActivityLevel(ActivityLevels.ACTIVE);
        account.setCountry("Tonga");
        account.setGenre(Genre.MALE);
        account.setBirthDate(LocalDate.parse("01/01/1980", formatter));

        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        WelcomePageBase welcomePage = homePage.signup();
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page is not opened");

        AccountCreationBase accountPage = welcomePage.clickContinue();
        Assert.assertTrue(accountPage.isPageOpened(), "Account creation page is not opened");

        accountPage.insertName(account.getFirstName());
        accountPage.pickGoals(account.getGoals());
        accountPage.processGoals(account.getGoalOptions());
        accountPage.processActivityLevel(account.getActivityLevel());
        accountPage.manageDemographic(account.getGenre(),account.getCountry(),account.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        accountPage.manageBodyData(account.getHeight_cm(),account.getWeight_kg(),70F);

        if(account.getGoals().contains(Goals.LOSE_WEIGHT) || account.getGoals().contains(Goals.GAIN_WEIGHT)){
            accountPage.proceedWeightGoal(GoalsLoseWeight.LOSE_HALF.getOriginalText());
        }


        accountPage.submitCredentials(email,password);
        accountPage.createUsername(username);

        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();
        loginPage.doLogin(email,password);

    }
}
