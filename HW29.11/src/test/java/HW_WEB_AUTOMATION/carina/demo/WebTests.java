package HW_WEB_AUTOMATION.carina.demo;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class WebTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "Favio")
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testMenShoesPage() throws InterruptedException {
        _HomePageBase homePage = initPage(getDriver(), _HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        homePage.openMenShoesPage();
        _MenShoesPageBase shoesPage = initPage(getDriver(), _MenShoesPageBase.class);
        Assert.assertTrue(shoesPage.isPageOpened(), "Men shoes page is not opened");

        Map<String, ExtendedWebElement> filters = shoesPage.getFiltersName();

        Assert.assertTrue(filters.containsKey("Marca"),"No brand filter");
        Assert.assertTrue(filters.containsKey("Género"),"No genre filter");
        Assert.assertFalse(filters.containsKey("Tipo"),"No type filter");

        Map<String,ExtendedWebElement> genres = shoesPage.getOptionsFromFilterType(filters.get("Género"));
        genres.get("Mujer").click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(100000));
        By overlayLocator = By.cssSelector("div.underlay");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));

        filters = shoesPage.getFiltersName(); // refresh this because of DOM reaload?

        Map<String,ExtendedWebElement> brands = shoesPage.getOptionsFromFilterType(filters.get("Marca"));
        brands.get("adidas").click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
        filters = shoesPage.getFiltersName(); // refresh this because of DOM reaload?

        Map<String,ExtendedWebElement> sizes = shoesPage.getOptionsFromFilterType(filters.get("Talle"));
        sizes.get("36").click();

        Assert.assertTrue(shoesPage.checkSelectedFilter("adidas"),"adidas not selected");
        Assert.assertTrue(shoesPage.checkSelectedFilter("Mujer"),"Mujer not selected");
        Assert.assertTrue(shoesPage.checkSelectedFilter("36"),"44 not selected");
    }
    @Test
    @MethodOwner(owner = "Favio")
    public void testSearch(){
        _HomePageBase homePage = initPage(getDriver(), _HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        _SearchPageBase searchPage = homePage.searchItems("River Plate");
        Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");
        Assert.assertFalse(searchPage.isEmpty(), "Search results not found");

        searchPage = homePage.searchItems("Random");
        Assert.assertTrue(searchPage.isEmpty(), "Search results not found");

        searchPage = homePage.searchItems("Boca Juniors");
        Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");
        Assert.assertFalse(searchPage.isEmpty(), "Search results not found");
    }
    @Test
    @MethodOwner(owner = "Favio")
    public void testOpeningCategories(){

        _HomePageBase homePage = initPage(getDriver(), _HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        Map<String,ExtendedWebElement> categories = homePage.getCategories();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(categories.containsKey("Running"),"There is no Running category");
        softAssert.assertFalse(categories.containsKey("Dancing"),"There is no dancing category");

        homePage.openCategory(categories.get("Running"));
        softAssert.assertFalse(homePage.isPageOpened(), "Home page is not opened");
        homePage.open();
        categories = homePage.getCategories();
        homePage.openCategory(categories.get("Outdoor"));
        softAssert.assertFalse(homePage.isPageOpened(), "Home page is not opened");
        homePage.open();
        categories = homePage.getCategories();
        homePage.openCategory(categories.get("Motorsport"));
        softAssert.assertFalse(homePage.isPageOpened(), "Home page is not opened");
        softAssert.assertAll();
    }
    @Test
    @MethodOwner(owner = "Favio")
    public void testLogin(){
        _LoginBasePage loginPage = initPage(getDriver(), _LoginBasePage.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        Assert.assertFalse(loginPage.isLoggedIn(),"User is logged in already");

        loginPage.setEmailField("test@test.com");
        loginPage.setPasswordField("testtest32test");
        loginPage.login();
        Assert.assertFalse(loginPage.isLoggedIn(),"User is logged in already");

        loginPage.setEmailField("lih82399@inohm.com");
        loginPage.setPasswordField("12345678abc");
        loginPage.login();
        Assert.assertTrue(loginPage.isLoggedIn(),"User is not active");

        loginPage.logout();
        Assert.assertFalse(loginPage.isLoggedIn(),"User is logged in already");

    }

    @Test
    @MethodOwner(owner = "Favio")
    public void testCheckSorting(){
        _HomePageBase homePage = initPage(getDriver(), _HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        homePage.openMenShoesPage();
        _MenShoesPageBase shoesPage = initPage(getDriver(), _MenShoesPageBase.class);
        Assert.assertTrue(shoesPage.isPageOpened(), "Men shoes page is not opened");

        SoftAssert softAssert = new SoftAssert();

        List<String> methods = shoesPage.getSortMethods();
        softAssert.assertTrue(methods.contains("product-discount"), "product-discount not present");
        softAssert.assertTrue(methods.contains("most-sold"), "most-sold not present");
        softAssert.assertFalse(methods.contains("product-new"), "product-new not present");
        softAssert.assertAll();

        for(String sortMethod: methods){
            shoesPage.changeSortMethod(sortMethod);
            Assert.assertEquals(shoesPage.getCurrentSortMethod(),sortMethod,"current sort method is not "+sortMethod);
        }
    }
}
