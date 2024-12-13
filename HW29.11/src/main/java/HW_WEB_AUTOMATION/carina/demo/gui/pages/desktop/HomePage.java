package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;


import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.HomePageBase;

import com.zebrunner.carina.utils.factory.DeviceType;

import org.openqa.selenium.WebDriver;


@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    public HomePage(WebDriver driver) {
        super(driver);
        setPageURL("/home");
    }




}
