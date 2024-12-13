package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SearchPageBase extends BasePage {
    public SearchPageBase(WebDriver driver) {
        super(driver);
    }


    public abstract boolean isSearchSuccessful();
}
