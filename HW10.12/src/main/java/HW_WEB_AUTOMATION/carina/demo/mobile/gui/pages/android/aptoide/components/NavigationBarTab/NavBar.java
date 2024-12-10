package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.components.NavigationBarTab;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.*;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class NavBar extends NavBarBase{

    public NavBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public AbstractPage openPage(Pages page){
        ExtendedWebElement element = findExtendedWebElement(By.id(page.getNameId()));
        element.click();
        switch (page){
            case EDITORIAL:
                return initPage(getDriver(), EditorialPageBase.class);
            case SEARCH:
                return initPage(getDriver(), SearchPageBase.class);
            case APPS:
                return initPage(getDriver(), AppsPageBase.class);
            case STORES:
                return initPage(getDriver(), StorePageBase.class);
            default:
                return initPage(getDriver(), HomePageBase.class);
        }
    }
}
