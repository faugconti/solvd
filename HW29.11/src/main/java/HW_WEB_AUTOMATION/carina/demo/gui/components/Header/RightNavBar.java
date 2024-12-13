package HW_WEB_AUTOMATION.carina.demo.gui.components.Header;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.SearchPageBase;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RightNavBar extends AbstractUIObject{
    public RightNavBar(WebDriver driver, SearchContext searchContext) {
        super(driver,searchContext);
    }




    @FindBy(tagName = "img")
    private ExtendedWebElement searchBtn;

    @FindBy(tagName = "input")
    private ExtendedWebElement searchInput;


    public SearchPageBase searchItems(String itemName){
        searchBtn.click();
        searchInput.click();
        searchInput.type(itemName);
        searchInput.sendKeys(Keys.ENTER);
        return new SearchPage(getDriver());
    }
}
