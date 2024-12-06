package HW_WEB_AUTOMATION.carina.demo.gui.components;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop.fitnesspal.enums.Genre;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CreationForm extends AbstractUIObject {


    public CreationForm(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    @FindBy(xpath = "//input[@value='M']")
    private ExtendedWebElement inputMale;
    @FindBy(xpath = "//input[@value='F']")
    private ExtendedWebElement inputFemale;
    @FindBy(xpath = "//input[@id='birthday']")
    private ExtendedWebElement calendar;
    @FindBy(xpath = "//div[@role='button']")
    private ExtendedWebElement countrySelector;
    @FindBy(xpath = "//ul[contains(@class,'MuiList-root')]")
    private ExtendedWebElement countryList;
    @FindBy(id = "Height")
    private ExtendedWebElement heightInput;
    @FindBy(id="Current weight")
    private ExtendedWebElement currentWeightInput;
    @FindBy(id="Goal weight")
    private ExtendedWebElement goalWeightInput;

    //create new comp
    @FindBy(id = "Email address")
    private ExtendedWebElement emailInput;
    @FindBy(id = "Create a password")
    private ExtendedWebElement passwordInput;
    @FindBy(xpath = "//input[@type='checkbox']")
    private ExtendedWebElement checkbox;

    //create new comp
    @FindBy(xpath = "(//button[@type='button'])[1]//p")
    private ExtendedWebElement switchBtn;
    @FindBy(xpath = "//span[contains(text(),'Centimeters')]")
    private ExtendedWebElement centimeters;
    @FindBy(xpath = "//Button//span[contains(text(),'Done')]")
    private ExtendedWebElement doneBtn;

    @FindBy(id = "Create a username")
    private ExtendedWebElement usernameInput;


    public void chooseGenre(Genre genre){
        switch(genre){
            case FEMALE:
                inputFemale.click();
                break;
            case MALE:
                inputMale.click();
                break;
        }
    }

    public void chooseCountry(String country){
        countrySelector.click();
        countryList.findExtendedWebElements(By.xpath("//li[contains(@role,'option')]"))
                .stream()
                .filter(countryLi -> countryLi.getText().equals(country))
                .findFirst()
                .ifPresent(ExtendedWebElement::click);
    }

    public void chooseDate(String formatedDate){
        calendar.click();
        calendar.type(formatedDate);
    }


    public void setHeight(float height){
        switchBtn.click();
        centimeters.click();
        doneBtn.click();
        heightInput.click();
        heightInput.type(String.valueOf(height));
    }

    public void setCurrentWeight(float currentWeight){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ExtendedWebElement switchBtn = findExtendedWebElement(By.xpath("(//button[@type='button'])[2]"));
        switchBtn.click();
        ExtendedWebElement cmRadio = findExtendedWebElement(By.xpath("(//input[@type='radio'])[1]"));
        cmRadio.click();
        ExtendedWebElement doneBtn = findExtendedWebElement(By.xpath("//Button//p[contains(text(),'Done')]"));
        doneBtn.click();
        currentWeightInput.click();
        currentWeightInput.type(String.valueOf(currentWeight));
    }

    public void setGoalWeightInput(float goalWeight){
        goalWeightInput.click();
        goalWeightInput.type(String.valueOf(goalWeight));
    }

    public void setEmail(String email){
        emailInput.click();
        emailInput.type(email);
    }

    public void setPassword(String password){
        passwordInput.click();
        passwordInput.type(password);
    }

    public void setUsername(String username){
        usernameInput.click();
        usernameInput.type(username);
    }

    public void acceptTerms(){
        checkbox.click();
    }
}
