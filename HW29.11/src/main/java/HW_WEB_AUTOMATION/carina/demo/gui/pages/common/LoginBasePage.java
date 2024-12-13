package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.LoginInputType;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class LoginBasePage extends BasePage {
    public LoginBasePage(WebDriver driver) {
        super(driver);
    }

    public abstract void writeInputField(LoginInputType operation, String payload);

    public abstract boolean isLoggedIn();

    public abstract void login();

    public abstract void logout();
}
