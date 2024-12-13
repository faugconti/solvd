package HW_WEB_AUTOMATION.carina.demo.gui.pages.desktop;

import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.ProductPageBase;
import HW_WEB_AUTOMATION.carina.demo.gui.pages.common.enums.SubFilters;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.*;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {
    public ProductPage(WebDriver driver,String url) {
        super(driver,url);
    }


    @FindBy(id = "product-search-results")
    private ExtendedWebElement sorterContainer;

    @Context(dependsOn = "sorterContainer")
    @FindBy(tagName = "select")
    private ExtendedWebElement sortSelector;

    @FindBy(className = "refinements")
    private ExtendedWebElement filterContainer;

    @FindBy(className = "pills-container")
    private ExtendedWebElement selectedFilters;

    @FindBy(xpath = "//div[starts-with(@id, 'refinement-pill-card')]")
    private List<ExtendedWebElement> filtersContainer;

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

    @Override
    public List<String> getFiltersName(){
        List<String> filterList =  new ArrayList<>();
        for(ExtendedWebElement filterSelect: filtersContainer){
            String filterName = filterSelect.findExtendedWebElement(By.tagName("h2")).getText();
            filterList.add(filterName);
        }
        return filterList;
    }

    @Override
    public void setFilter(SubFilters subFilter){
        String filterName = subFilter.getFilterName().getName();
        String optionName = subFilter.getName();
        String modifiedName = filterName.toLowerCase().replace("\\s","-");

        System.out.println("refinement-pill-card-"+modifiedName);
        ExtendedWebElement filterDiv = filterContainer.findExtendedWebElement(By.id("refinement-pill-card-"+modifiedName));
        filterDiv.findExtendedWebElement(By.tagName("a")).click();

        List<ExtendedWebElement> options = filterDiv.findExtendedWebElements(By.tagName("li"));
        for(ExtendedWebElement option:options){
            String name = option.findExtendedWebElement(By.tagName("span")).getText();
            if(name.equals(optionName)){
                option.findExtendedWebElement(By.tagName("button")).click();
                break;
            }

        }

    }

    @Override
    public boolean checkSelectedFilter(SubFilters filter){
        for(ExtendedWebElement element: selectedFilters.findExtendedWebElements(By.tagName("h2"))){
            if(element.getText().equals(filter.getName())){
                return true;
            }
        }
        return false;
    }

}
