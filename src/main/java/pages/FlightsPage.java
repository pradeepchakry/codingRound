package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;

import java.util.Iterator;
import java.util.List;

public class FlightsPage {
    WebDriver driver;

    By fromField = By.id("FromTag");
    By toField = By.id("ToTag");
    By calendarIcon = By.xpath("//*[@class='calendarIcon']/i");
    By selectedDate = By.xpath("//td[contains(@class, 'selected')]");
    By searchButton = By.id("SearchBtn");
    By oneWayRadioButton = By.id("OneWay");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOneWayRadioButton() {
        driver.findElement(oneWayRadioButton).click();
    }

    public void enterFromField(String fromCity) {
        driver.findElement(fromField).clear();
        driver.findElement(fromField).sendKeys(fromCity);
    }

    public void enterDestinationField(String toCity) {
        driver.findElement(toField).clear();
        driver.findElement(toField).sendKeys(toCity);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public void clickCalendarIcon() {
        driver.findElement(calendarIcon).click();
    }

    public void chooseDefaultDate() {
        clickCalendarIcon();
        driver.findElement(selectedDate).click();
    }

    public void fillFromCityField(String fromCity) {
        enterFromField(fromCity);
        Commons.waitFor(10000);
        List<WebElement> list = getAutoCompleteList("from");
        pickNameFromAutoComplete(list, fromCity).click();
    }

    public void fillToCityField(String toCity) {
        enterDestinationField(toCity);
        Commons.waitFor(10000);
        List<WebElement> list = getAutoCompleteList("to");
        pickNameFromAutoComplete(list, toCity).click();
    }

    public List<WebElement> getAutoCompleteList(String flag) {
        List<WebElement> options = null;
        if(flag.equals("from")) {
            options = driver.findElement(By.id("ui-id-1"))
                    .findElements(By.tagName("li"));
        } else if(flag.equals("to")) {
            options = driver.findElement(By.id("ui-id-2"))
                    .findElements(By.tagName("li"));
        } else {
            System.out.println("Invalid flag to get the autocomplete list");
        }
        return options;
    }

    public WebElement pickNameFromAutoComplete(List<WebElement> list, String searchString) {
        WebElement element = null;
        Iterator<WebElement> iteratorObj = list.iterator();
        while(iteratorObj.hasNext()) {
            element = iteratorObj.next();
            if(element != null) {
                if(element.getText().contains(searchString)) {
                    return  element;
                } else {
                    System.out.println("Problem with input. No suggestions for the string '"
                            + searchString + "'. Returning null.");
                }
            }
        }
        return element;
    }
}
