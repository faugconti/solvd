package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class _LoginBasePage extends AbstractPage {
    public _LoginBasePage(WebDriver driver) {
        super(driver);
    }

    public abstract void setPasswordField(String password);

    public abstract void setEmailField(String email);

    public abstract boolean isLoggedIn();

    public abstract void login();

    public abstract void logout();
}
