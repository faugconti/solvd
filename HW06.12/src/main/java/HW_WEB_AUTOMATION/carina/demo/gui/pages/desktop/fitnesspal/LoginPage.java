package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase{

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("account/login");
    }

    @FindBy(id = "email")
    private ExtendedWebElement emailInput;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private ExtendedWebElement loginBtn;

    @Override
    public void doLogin(String email, String password){
        emailInput.click();
        emailInput.type(email);
        passwordInput.click();
        passwordInput.type(password);
        loginBtn.click();
    }
}
