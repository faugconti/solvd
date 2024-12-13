package HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;

import HW_WEB_AUTOMATION.carina.demo.mobile.gui.pages.common.ChartsPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ChartsPageBase.class)
public class ChartsPage extends ChartsPageBase {

    public ChartsPage(WebDriver driver) {
        super(driver);
    }

}
