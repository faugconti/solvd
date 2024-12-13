package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public abstract class _MenShoesPageBase extends AbstractPage {
    public _MenShoesPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract Map<String, ExtendedWebElement> getFiltersName();
    public abstract Map<String,ExtendedWebElement> getOptionsFromFilterType(ExtendedWebElement filterName);
    public abstract boolean checkSelectedFilter(String filterName);

    public abstract List<String> getSortMethods();

    public abstract void changeSortMethod(String newOption);

    public abstract String getCurrentSortMethod();
}
