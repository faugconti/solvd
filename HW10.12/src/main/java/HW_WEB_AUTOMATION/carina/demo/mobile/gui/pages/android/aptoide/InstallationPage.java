package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android.aptoide.common.InstallationPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = InstallationPageBase.class)
public class InstallationPage extends InstallationPageBase{

    @FindBy(id = "app_name")
    private ExtendedWebElement appTitle;

    @FindBy(id = "appview_install_button")
    private ExtendedWebElement installBtn;

    public InstallationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return installBtn.isPresent();
    }
    @Override
    public boolean matchNames(String query){
        return appTitle.getText().toLowerCase().contains(query.toLowerCase());
    }


}
