package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;


import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.StorePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.components.NavigationBarTab.NavBar;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = StorePageBase.class)
public class StorePage extends StorePageBase {

    @FindBy(xpath = "//android.widget.TextView[@resource-id='cm.aptoide.pt:id/title' and @text='Recommended stores']")
    private ExtendedWebElement recommendedTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='cm.aptoide.pt:id/title' and @text='Followed stores']")
    private ExtendedWebElement followedTitle;

    @FindBy(id = "largeLabel")
    private ExtendedWebElement menuIcon;

    @FindBy(id = "more")
    private ExtendedWebElement moreBtn;

    @FindBy(id = "bottom_navigation")
    private NavBar navBar;


    public StorePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return moreBtn.isPresent(10) &&
                menuIcon.isPresent(10) &&
                followedTitle.isPresent(10) &&
                recommendedTitle.isPresent(10);
    }

    @Override
    public AbstractPage openPage(Pages page) {
        return navBar.openPage(page);
    }
}
