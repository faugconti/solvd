package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common._HomePageBase;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common._MenShoesPageBase;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common._SearchPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = _HomePageBase.class)
public class _HomePage extends _HomePageBase {

    @FindBy(id= "hombre")
    private ExtendedWebElement mensButton;

    @FindBy(id = "hombre-calzado")
    private ExtendedWebElement mensShoes;

    @FindBy(id = "categorias")
    private ExtendedWebElement categorias;

    @FindBy(id = "categorias-container")
    private ExtendedWebElement categoriesContainer;

    @FindBy(xpath = "//div[@class='navbar-header right-menu']")
    private ExtendedWebElement rightNavbar;

    @FindBy(tagName = "img")
    @Context(dependsOn = "rightNavbar")
    private ExtendedWebElement searchBtn;

    @FindBy(tagName = "input")
    @Context(dependsOn = "rightNavbar")
    private ExtendedWebElement searchInput;

    public _HomePage(WebDriver driver) {
        super(driver);
        setPageURL("/home");
    }

    @Override
    public _MenShoesPageBase openMenShoesPage(){
        mensButton.hover();
        mensShoes.click();
        return initPage(driver, _MenShoesPageBase.class);
    }

    @Override
    public _SearchPageBase searchItems(String itemName){
        searchBtn.click();
        searchInput.click();
        searchInput.type(itemName);
        searchInput.sendKeys(Keys.ENTER);
        return initPage(driver, _SearchPageBase.class);
    }

    @Override
    public Map<String,ExtendedWebElement> getCategories(){
        categorias.hover();
        Map<String,ExtendedWebElement> categories = new HashMap<>();
        for(ExtendedWebElement element: categoriesContainer.findExtendedWebElements(By.tagName("li"))){
            ExtendedWebElement a = element.findExtendedWebElement(By.tagName("a"));
            categories.put(a.getText(),a);
        }
        return categories;
    }

    @Override
    public void openCategory(ExtendedWebElement element){
        categorias.hover();
        element.click();
    }


}
