package HW_WEB_AUTOMATION.carina.demo.gui.components.Header;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.ProductPageBase;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.SearchPageBase;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.Menus;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.SubMenuCategories;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.ProductPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MainHeader extends AbstractUIObject {

    @FindBy(xpath = "//ul[@role='menu']")
    private ExtendedWebElement menuList;

    @FindBy(xpath = "//div[contains(@class,'menu-items-container')]")
    private ExtendedWebElement subMenu;

    @FindBy(xpath = "//div[@class='navbar-header right-menu']")
    private RightNavBar rightNav;

    public MainHeader(WebDriver driver, SearchContext searchContext) {
        super(driver,searchContext);
    }

    public void hoverItem(Menus menu){
        ExtendedWebElement element = menuList.findExtendedWebElement(By.id(menu.getID()));
        element.hover();
    }

    public ProductPageBase openSubProductPage(SubMenuCategories subMenu){
        Menus menu = subMenu.getMenu();
        ExtendedWebElement baseElement = menuList.findExtendedWebElement(By.id(menu.getID()));
        baseElement.hover();

        ExtendedWebElement element = findExtendedWebElement(By.id(subMenu.getID()));
        element.click();
        return new ProductPage(getDriver(),subMenu.getUrl());
    }

    public SearchPageBase searchItems(String input){
        return rightNav.searchItems(input);
    }




}
