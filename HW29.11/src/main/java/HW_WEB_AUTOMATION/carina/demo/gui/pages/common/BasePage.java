package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import HW_WEB_AUTOMATION.carina.demo.gui.components.Header.MainHeader;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.SubMenuCategories;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "menuContainer")
    private MainHeader header;

    public ProductPageBase openProductPage(SubMenuCategories productType){
        return header.openSubProductPage(productType);
    }

    public SearchPageBase searchItems(String itemName){
        return header.searchItems(itemName);
    }

}
