package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = WelcomePageBase.class)

public class WelcomePage extends WelcomePageBase{

    @FindBy(tagName = "Button")
    private ExtendedWebElement continueButton;

    public WelcomePage(WebDriver driver) {
        super(driver);
        setPageURL("account/create/welcome");
    }
    @Override
    public AccountCreationBase clickContinue(){
        continueButton.click();
        return initPage(driver, AccountCreationBase.class);
    }
}
