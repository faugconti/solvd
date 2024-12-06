package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage {
    public HomePageBase(WebDriver driver) {
        super(driver);
        //setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
    }

    public abstract WelcomePageBase signup();
}
