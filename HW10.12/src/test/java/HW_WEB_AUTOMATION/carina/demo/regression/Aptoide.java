package HW_WEB_AUTOMATION.carina.demo.regression;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.*;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Aptoide implements IAbstractTest, IMobileUtils {

    @Test()
    @MethodOwner(owner = "Faconti")
    public void testOpeningPages(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(),"Home page isn't opened");

        EditorialPageBase editorialPage = (EditorialPage) homePage.openPage(Pages.EDITORIAL);
        Assert.assertTrue(editorialPage.isPageOpened(),"Editorial page isn't opened");

        StorePageBase storePage = (StorePageBase) editorialPage.openPage(Pages.STORES);
        Assert.assertTrue(storePage.isPageOpened(),"Store page isn't opened");

        AppsPageBase appsPage = (AppsPageBase) editorialPage.openPage(Pages.APPS);
        Assert.assertTrue(appsPage.isPageOpened(),"apps page isn't opened");

        SearchPageBase searchPage = (SearchPageBase) appsPage.openPage(Pages.SEARCH);
        Assert.assertTrue(searchPage.isPageOpened(),"search page isn't opened");

    }

    @Test()
    @MethodOwner(owner = "Faconti")
    public void searchApp() {
        String query = "wifi";

        HomePageBase homePage = initPage(getDriver(),HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");

        SearchPageBase searchPage = (SearchPageBase) homePage.openPage(Pages.SEARCH);
        Assert.assertTrue(searchPage.isPageOpened(), "Search page isn't opened");

        searchPage.searchApp(query);
        Assert.assertTrue(searchPage.isSearchPositive(), "Search page didn't found results");

        InstallationPageBase appPage = searchPage.goToFirstApp();
        Assert.assertTrue(appPage.isPageOpened(),"Installation page not opened");
        Assert.assertTrue(appPage.matchNames(query),"query does not match with the name");
    }

    @Test()
    @MethodOwner(owner = "Faconti")
    public void testStorePage() {
        HomePageBase homePage = initPage(getDriver(),HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");

        StorePageBase storePage = (StorePageBase) homePage.openPage(Pages.STORES);
        Assert.assertTrue(storePage.isPageOpened(),"Store page isn't opened");
    }

    @Test()
    @MethodOwner(owner = "Faconti")
    public void testAppsPage(){
        HomePageBase homePage = initPage(getDriver(),HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");

        AppsPageBase appsPage = (AppsPageBase) homePage.openPage(Pages.APPS);
        Assert.assertTrue(appsPage.isPageOpened(), "App page isn't opened");
    }

    @Test()
    @MethodOwner(owner = "Faconti")
    public void testHomePage(){
        HomePageBase homePage = initPage(getDriver(),HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");
        Assert.assertTrue(homePage.checkForTitles(),"titles not found");
    }

}
