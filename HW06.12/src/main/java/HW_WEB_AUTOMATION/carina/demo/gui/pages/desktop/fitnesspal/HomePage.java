package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase{

    @FindBy(xpath = "(//a[@href='/account/create/welcome'])[1]")
    private ExtendedWebElement compareMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @Override
    public WelcomePageBase signup(){
        compareMenu.click();
        return initPage(driver, WelcomePageBase.class);
    }


}
