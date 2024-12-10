package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.components.NavigationBarTab;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.*;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class NavBar extends NavBarBase{

    @FindBy(id = "action_home")
    private ExtendedWebElement homeBtn;

    @FindBy(id = "action_curation")
    private ExtendedWebElement editorialBtn;

    @FindBy(id = "action_search")
    private ExtendedWebElement searchBtn;

    @FindBy(id = "action_stores")
    private ExtendedWebElement storeBtn;

    @FindBy(id = "action_apps")
    private ExtendedWebElement appsBtn;



    public NavBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }



    public AbstractPage openPage(Pages page){
        switch (page){
            case EDITORIAL:
                editorialBtn.click();
                return initPage(getDriver(), EditorialPageBase.class);
            case SEARCH:
                searchBtn.click();
                return initPage(getDriver(), SearchPageBase.class);
            case APPS:
                appsBtn.click();
                return initPage(getDriver(), AppsPageBase.class);
            case STORES:
                storeBtn.click();
                return initPage(getDriver(), StorePageBase.class);
            default:
                homeBtn.click();
                return initPage(getDriver(), HomePageBase.class);
        }
    }
}
