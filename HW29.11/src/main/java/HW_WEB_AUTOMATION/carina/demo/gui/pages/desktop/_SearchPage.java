package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common._SearchPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = _SearchPageBase.class)
public class _SearchPage extends _SearchPageBase {
    public _SearchPage(WebDriver driver) {
        super(driver);
        setPageURL("/buscar");
    }

    @FindBy(xpath = "//div[contains(@class, 'result-count')]")
    private ExtendedWebElement resultCountDiv;

    @Override
    public boolean isEmpty(){

        try {
            if (resultCountDiv.findExtendedWebElement(By.className("search-result-count")) != null) {
                return false;
            }
        } catch (NoSuchElementException ignored) {

        }

        try {
            if (resultCountDiv.findExtendedWebElement(By.className("no-result-container")) != null) {
                return true;
            }
        } catch (NoSuchElementException ignored) {

        }

        throw new IllegalStateException("Unable to determine search results state!");
    }
}
