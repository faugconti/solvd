package HW_WEB_AUTOMATION.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class CreationModal extends AbstractUIObject {

    @FindBy(xpath = "//Button[@type='submit']")
    private ExtendedWebElement nextBtn;

    @FindBy(xpath = "//input[@id=':r0:']")
    private ExtendedWebElement inputName;

    @FindBy(xpath = "//div[@role='group']")
    private ExtendedWebElement goalsContainer;


    public CreationModal(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickNext(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@type='submit']")));
        nextBtn.click();
    }

    public void writeInput(String input){
        inputName.hover();
        inputName.click();
        inputName.type(input);
    }

    public void manageOptions(Set<String> optionTexts){
        for (ExtendedWebElement element : goalsContainer.findExtendedWebElements(By.tagName("button"))) {
            String optionText = element.findExtendedWebElement(By.tagName("p")).getText();
            if (optionTexts.contains(optionText)) {
                element.click();
            }
        }
    }




}
