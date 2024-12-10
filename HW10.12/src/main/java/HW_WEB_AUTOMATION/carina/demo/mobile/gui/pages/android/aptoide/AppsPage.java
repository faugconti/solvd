package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.AppsPageBase;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.components.NavigationBarTab.NavBar;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = AppsPageBase.class)
public class AppsPage extends AppsPageBase{

    @FindBy(xpath = "//android.widget.TextView[@resource-id='cm.aptoide.pt:id/apps_header_title' and @text='Updates']")
    private ExtendedWebElement titleUpdates;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='cm.aptoide.pt:id/apps_header_title' and @text='Installed']")
    private ExtendedWebElement titleInstalled;

    @FindBy(id = "updates_badge")
    private ExtendedWebElement menuIcon;

    @FindBy(id = "apps_header_button")
    private ExtendedWebElement updateBtn;

    @FindBy(id = "bottom_navigation")
    private NavBar navBar;

    public AppsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AbstractPage openPage(Pages page) {
        return navBar.openPage(page);
    }

    @Override
    public boolean isPageOpened(){
        return updateBtn.isPresent(10) &&
                menuIcon.isPresent(10) &&
                titleInstalled.isPresent(10) &&
                titleUpdates.isPresent(10);
    }


}
