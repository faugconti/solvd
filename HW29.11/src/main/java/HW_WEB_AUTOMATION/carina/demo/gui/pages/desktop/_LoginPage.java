package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common._LoginBasePage;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = _LoginBasePage.class)
public class _LoginPage extends _LoginBasePage {

    @FindBy(xpath = "//input[@type='email']")
    private ExtendedWebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "//div[contains(@class,'login-userPanel_Btn')]")
    private ExtendedWebElement loginPanel;

    public _LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("/login");
    }
    @Override
    public void setPasswordField(String password){
        passwordField.click();
        passwordField.type(password);
    }
    @Override
    public void setEmailField(String email){
        emailField.click();
        emailField.type(email);
    }
    @Override
    public boolean isLoggedIn(){

        //check if we have a presentation object
        loginPanel.hover();
        try{
            ExtendedWebElement presentation = loginPanel.findExtendedWebElement(By.xpath("//li[contains(@class,'right__menu__userName')]"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    @Override
    public void login(){
        if(this.isLoggedIn()){
            return;
        }
        WebElement loginBtn = getDriver().findElement(By.xpath("//button[@type='submit' and contains(@class,'btn-login')]"));
        loginBtn.click();
    }
    @Override
    public void logout(){
        if(!this.isLoggedIn()){
            return;
        }
        loginPanel.hover();
        WebElement logoutBtn = getDriver().findElement(By.id("Logout"));
        logoutBtn.click();
    }

}
