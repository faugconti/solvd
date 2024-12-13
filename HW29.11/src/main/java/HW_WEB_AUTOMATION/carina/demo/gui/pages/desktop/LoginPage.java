package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.LoginBasePage;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.LoginInputType;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginBasePage.class)
public class LoginPage extends LoginBasePage {


    @FindBy(xpath = "//div[contains(@class,'login-userPanel_Btn')]")
    private ExtendedWebElement loginPanel;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("/login");
    }
    @Override
    public void writeInputField(LoginInputType operation, String payload){
        List<ExtendedWebElement> elements = findExtendedWebElements(By.xpath("//form[@class='login']//input"));
        switch (operation){
            case USERNAME:
                elements.get(0).click();
                elements.get(0).type(payload);
            case PASSWORD:
                elements.get(1).click();
                elements.get(1).type(payload);
        }
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
