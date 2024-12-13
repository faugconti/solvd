package HW_WEB_AUTOMATION.carina.demo;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.*;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class WebTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "Favio")
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testMenShoesPage() throws InterruptedException {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        ProductPageBase shoesPage = homePage.openProductPage(SubMenuCategories.MEN_SHOES);
        Assert.assertTrue(shoesPage.isPageOpened(), "Men shoes page is not opened");

        List<String> filters = shoesPage.getFiltersName();

        Assert.assertTrue(filters.contains(ProductFilter.BRAND.getName()),"No brand filter");
        Assert.assertTrue(filters.contains(ProductFilter.GENRE.getName()),"No genre filter");
        Assert.assertTrue(filters.contains(ProductFilter.TYPE.getName()),"No type filter");


        shoesPage.setFilter(SubFilters.FEMALE);
        shoesPage.refresh(2);
        //WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(100000));
        //By overlayLocator = By.cssSelector("div.underlay");
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));

        shoesPage.setFilter(SubFilters.ADIDAS);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
        shoesPage.refresh(2);
        shoesPage.setFilter(SubFilters.THIRTY_SIX);
        shoesPage.refresh(2);
        Assert.assertTrue(shoesPage.checkSelectedFilter(SubFilters.ADIDAS),"adidas not selected");
        Assert.assertTrue(shoesPage.checkSelectedFilter(SubFilters.FEMALE),"Mujer not selected");
        Assert.assertTrue(shoesPage.checkSelectedFilter(SubFilters.THIRTY_SIX),"36 not selected");
    }
    @Test
    @MethodOwner(owner = "Favio")
    public void testSearch(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        SearchPageBase searchPage = homePage.searchItems("River Plate");
        Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");

        Assert.assertTrue(searchPage.isSearchSuccessful(), "Search results not found");

        searchPage = homePage.searchItems("Random");
        Assert.assertFalse(searchPage.isSearchSuccessful(), "Search results not found");

        searchPage = homePage.searchItems("Boca Juniors");
        Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");
        Assert.assertTrue(searchPage.isSearchSuccessful(), "Search results not found");
    }

    @Test
    @MethodOwner(owner = "Favio")
    public void testLogin(){
        LoginBasePage loginPage = initPage(getDriver(), LoginBasePage.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        Assert.assertFalse(loginPage.isLoggedIn(),"User is logged in already");

        loginPage.writeInputField(LoginInputType.USERNAME,R.TESTDATA.get("test_username"));
        loginPage.writeInputField(LoginInputType.PASSWORD,R.TESTDATA.get("user_password"));
        loginPage.login();
        Assert.assertFalse(loginPage.isLoggedIn(),"User is logged in already");

        loginPage.writeInputField(LoginInputType.USERNAME,R.TESTDATA.get("user_username"));
        loginPage.writeInputField(LoginInputType.PASSWORD,R.TESTDATA.get("user_password"));
        loginPage.login();
        Assert.assertTrue(loginPage.isLoggedIn(),"User is not active");

        loginPage.logout();
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        Assert.assertFalse(loginPage.isLoggedIn(),"User is logged in already");
        Assert.assertTrue(homePage.isPageOpened(),"User was not redirect to homePage");
    }

    @Test
    @MethodOwner(owner = "Favio")
    public void testCheckSorting(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        ProductPageBase shoesPage = homePage.openProductPage(SubMenuCategories.MEN_SHOES);
        Assert.assertTrue(shoesPage.isPageOpened(), "Men shoes page is not opened");

        SoftAssert softAssert = new SoftAssert();
        List<String> methods = shoesPage.getSortMethods();

        softAssert.assertTrue(methods.contains(SortingMethods.HIGHEST_PRICE.getName()), "product-discount not present");
        softAssert.assertTrue(methods.contains(SortingMethods.MOST_SOLD.getName()), "most-sold not present");
        softAssert.assertTrue(methods.contains(SortingMethods.NEWEST_PRODUCT.getName()), "product-new not present");

        softAssert.assertAll();

        for(SortingMethods sortMethod: SortingMethods.values()){
            shoesPage.changeSortMethod(sortMethod.getName());
            Assert.assertEquals(shoesPage.getCurrentSortMethod(),sortMethod.getName(),"current sort method is not "+sortMethod);
        }
    }

    @Test
    @MethodOwner(owner = "Favio")
    public void testOpeningCategories(){

        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        SoftAssert softAssert = new SoftAssert();
        ProductPageBase productPage = homePage.openProductPage(SubMenuCategories.MOTORSPORT);

        softAssert.assertTrue(productPage.isPageOpened(),"Motorsport page is not opened");
        ProductPageBase bootsPage = productPage.openProductPage(SubMenuCategories.BOOTS);
        softAssert.assertFalse(productPage.isPageOpened(),"Male Boots page opened");
        softAssert.assertTrue(bootsPage.isPageOpened(),"Male Boots page is not opened");
        productPage = bootsPage.openProductPage(SubMenuCategories.RUNNING);
        softAssert.assertTrue(productPage.isPageOpened(),"Running page is not opened");
        ProductPageBase voleyPage = productPage.openProductPage(SubMenuCategories.VOLEY);
        softAssert.assertTrue(voleyPage.isPageOpened(),"Voley page is not opened");

        softAssert.assertAll();
    }



}
