package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.ProductFilter;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.SubFilters;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.SubMenuCategories;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class ProductPageBase extends BasePage {
    public ProductPageBase(WebDriver driver,String url) {
        super(driver);
        setPageURL(url);
    }

    public String getPageUrl(){
        return this.pageURL;
    }

    public abstract List<String> getSortMethods();

    public abstract void changeSortMethod(String newOption);

    public abstract String getCurrentSortMethod();

    public abstract List<String> getFiltersName();

    public abstract void setFilter(SubFilters subFilter);

    public abstract boolean checkSelectedFilter(SubFilters filter);
}
