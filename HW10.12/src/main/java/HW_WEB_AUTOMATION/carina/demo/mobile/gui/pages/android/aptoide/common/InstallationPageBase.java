package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class InstallationPageBase extends AbstractPage {



    public InstallationPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean matchNames(String query);
}
