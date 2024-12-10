package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.IPageOpener;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage implements IPageOpener {
    public HomePageBase(WebDriver driver) {
        super(driver);
    }


    public abstract void proceed();

    public abstract boolean checkForTitles();
}
