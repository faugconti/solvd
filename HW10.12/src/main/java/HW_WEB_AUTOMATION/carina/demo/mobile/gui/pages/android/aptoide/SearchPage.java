package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.InstallationPageBase;
import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.SearchPageBase;
import com.zebrunner.carina.utils.android.IAndroidUtils;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SearchPageBase.class)
public class SearchPage extends SearchPageBase implements IAndroidUtils{


    @FindBy(id = "search_bar")
    private ExtendedWebElement searchBar;

    @FindBy(id = "fragment_search_result_all_stores_app_list")
    private ExtendedWebElement validResults;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return searchBar.isPresent(10);
    }

    @Override
    public void searchApp(String appName) {
        searchBar.click();
        ExtendedWebElement searchBarInput = searchBar.findExtendedWebElement(By.id("search_src_text"));
        searchBarInput.type(appName);
        pressKeyboardKey(AndroidKey.ENTER);
    }

    @Override
    public boolean isSearchPositive(){

        try{
            return validResults.isPresent(10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstallationPageBase goToFirstApp(){
        validResults.findExtendedWebElements(By.xpath("//android.widget.FrameLayout")).get(0).click();
        return initPage(getDriver(), InstallationPageBase.class);
    }
}
