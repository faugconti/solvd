package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.IPageOpener;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AppsPageBase extends AbstractPage implements IPageOpener {
    public AppsPageBase(WebDriver driver) {
        super(driver);
    }



}
