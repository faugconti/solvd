package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.EditorialPageBase;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.components.NavigationBarTab.NavBar;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = EditorialPageBase.class)
public class EditorialPage extends EditorialPageBase{

    @FindBy(id = "refresh_layout")
    private ExtendedWebElement appContainer;

    @FindBy(xpath = "//android.widget.TextView[@text='Editorial']")
    private ExtendedWebElement menuIcon;

    @FindBy(id = "bottom_navigation")
    private NavBar navBar;

    public EditorialPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return appContainer.isPresent() &&
                menuIcon.isPresent();
    }

    @Override
    public AbstractPage openPage(Pages page) {
        return navBar.openPage(page);
    }
}
