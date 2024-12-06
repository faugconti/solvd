package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class _SearchPageBase extends AbstractPage {
    public  _SearchPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isEmpty();
}
