package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class LoginPageBase extends AbstractPage {
    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void doLogin(String email, String password);
}
