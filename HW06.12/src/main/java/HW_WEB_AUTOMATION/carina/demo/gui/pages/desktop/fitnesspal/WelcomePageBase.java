package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class WelcomePageBase extends AbstractPage {

    @FindBy(xpath = ".//iframe[@id='sp_message_iframe_1164399']")
    private ExtendedWebElement iframe;

    public WelcomePageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);

        CookiesIFrame frame = new CookiesIFrame(driver);
        driver.switchTo().frame(iframe.getElement());
        frame.click();
        getDriver().switchTo().defaultContent();
    }

    public abstract AccountCreationBase clickContinue();
}
