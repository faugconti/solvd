package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CookiesIFrame extends AbstractPage {

    @FindBy(xpath = "//Button[@title='OK']")
    private ExtendedWebElement component;

    public CookiesIFrame(WebDriver driver) {
        super(driver);
    }

    public void click() {
        component.click();
    }
}