package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common._MenShoesPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = _MenShoesPageBase.class)
public class _MenShoesPage extends _MenShoesPageBase{
    public _MenShoesPage(WebDriver driver) {
        super(driver);
        setPageURL("/hombre/calzado");
    }

    @FindBy(className = "refinements")
    private ExtendedWebElement filterContainer;

    @FindBy(className = "pills-container")
    private ExtendedWebElement selectedFilters;

    @FindBy(xpath = "//div[starts-with(@id, 'refinement-pill-card')]")
    private List<ExtendedWebElement> filters;

    @FindBy(id = "product-search-results")
    private ExtendedWebElement sorterContainer;

    @Context(dependsOn = "sorterContainer")
    @FindBy(tagName = "select")
    private ExtendedWebElement sortSelector;

    @Override
    public Map<String,ExtendedWebElement> getFiltersName(){
        HashMap<String,ExtendedWebElement> filterMap =  new HashMap<>();;
        for(ExtendedWebElement filterContainer: filters){
            String filterName = filterContainer.findExtendedWebElement(By.tagName("h2")).getText();
            filterMap.put(filterName,filterContainer);
        }
        return filterMap;
    }

    @Override
    public Map<String,ExtendedWebElement> getOptionsFromFilterType(ExtendedWebElement filterName){
        HashMap<String,ExtendedWebElement> optionsMap =  new HashMap<>();
        System.out.println(filterName.getElement().getAttribute("id"));
        filterName.findExtendedWebElement(By.tagName("a")).click(); // a btn
        List<ExtendedWebElement> options = filterName.findExtendedWebElements(By.tagName("li"));
        for(ExtendedWebElement option:options){
            String name = option.findExtendedWebElement(By.tagName("span")).getText(); // first span
            ExtendedWebElement element = option.findExtendedWebElement(By.tagName("button"));
            optionsMap.put(name,element);
        }

        return optionsMap;
    }
    @Override
    public boolean checkSelectedFilter(String filterName){
        for(ExtendedWebElement element: selectedFilters.findExtendedWebElements(By.tagName("h2"))){
            if(element.getText().equals(filterName)){
                return true;
            }
        }
        return false;
    }
    @Override
    public List<String> getSortMethods(){
        List<String> options = new ArrayList<>();
        for(ExtendedWebElement option : sortSelector.findExtendedWebElements(By.tagName("option"))){
            options.add(option.getAttribute("data-id"));
        }
        return options;
    }
    @Override
    public void changeSortMethod(String newOption){
        sortSelector.click();
        for(ExtendedWebElement option : sortSelector.findExtendedWebElements(By.tagName("option"))){
            if(option.getAttribute("data-id").equals(newOption)){
                option.click();
            }
        }
    }
    @Override
    public String getCurrentSortMethod(){
        return sorterContainer.getAttribute("data-srule");
    }
}
