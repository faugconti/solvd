package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;


public abstract class _HomePageBase extends AbstractPage {


    public  _HomePageBase(WebDriver driver) {
        super(driver);

    }

    public abstract _MenShoesPageBase openMenShoesPage();
    public abstract _SearchPageBase searchItems(String itemName);
    public abstract Map<String, ExtendedWebElement> getCategories();
    public abstract void openCategory(ExtendedWebElement element);
}
