package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;


import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.*;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.components.NavigationBarTab.NavBar;
import com.zebrunner.carina.utils.android.IAndroidUtils;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IAndroidUtils{


    @FindBy(id = "skip_button")
    private ExtendedWebElement skipBtn;

    @FindBy(id = "bottom_navigation")
    private NavBar navBar;

    @FindBy(id = "bundle_more")
    private ExtendedWebElement moreBtn;

    @FindBy(id = "bundle_title")
    private ExtendedWebElement editorTitle;

    @FindBy(id = "app_bar_layout")
    private ExtendedWebElement gamesAppsBar;


    @FindBy(xpath = "//android.widget.TextView[@text='Use Your AppCoins']")
    private ExtendedWebElement coinsApp;

    @FindBy(xpath = "//android.widget.TextView[@text='Idle Games']")
    private ExtendedWebElement iGames;

    @FindBy(xpath = "//android.widget.TextView[@text='Top Games']")
    private ExtendedWebElement topGames;

    public HomePage(WebDriver driver) {
        super(driver);
        this.proceed();
    }


    @Override
    public boolean isPageOpened(){
        return editorTitle.isPresent(10)
                && moreBtn.isPresent(10)
                && gamesAppsBar.isPresent(10);
    }

    @Override
    public void proceed(){
        skipBtn.click();
    }

    @Override
    public boolean checkForTitles(){
        return checkForTitle(coinsApp) &&
                checkForTitle(topGames) &&
                checkForTitle(iGames);
    }

    private boolean checkForTitle(ExtendedWebElement element){
        swipe(element,5);
        return  element.isPresent(10);
    }

    @Override
    public AbstractPage openPage(Pages page) {
        return navBar.openPage(page);
    }


}
