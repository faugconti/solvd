package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SearchPageBase extends AbstractPage {
    public SearchPageBase(WebDriver driver) {
        super(driver);
    }

    abstract public void searchApp(String appName);

    public abstract boolean isSearchPositive();

    public abstract InstallationPageBase goToFirstApp();
}
