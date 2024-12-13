package HW_WEB_AUTOMATION.carina.demo.gui.pages.common;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.SubMenuCategories;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.kohsuke.rngom.parse.host.Base;
import org.openqa.selenium.WebDriver;

import java.util.Map;


public abstract class HomePageBase extends BasePage {


    public  HomePageBase(WebDriver driver) {
        super(driver);

    }

}
