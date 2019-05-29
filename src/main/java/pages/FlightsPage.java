package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Commons;
import utils.TestUtils;

import java.util.Iterator;
import java.util.List;

public class FlightsPage {
    WebDriver driver;

    @FindBy(id = "FromTag")
    private WebElement fromField;

    @FindBy(id = "ToTag")
    private WebElement toField;

    @FindBy(xpath = "//*[@class='calendarIcon']/i")
    private WebElement calendarIcon;

    @FindBy(xpath = "//td[contains(@class, 'selected')]")
    private WebElement selectedDate;

    @FindBy(id = "SearchBtn")
    private WebElement searchButton;

    @FindBy(id = "OneWay")
    private WebElement oneWayRadioButton;

    @FindBy(className = "searchSummary")
    private WebElement searchSummary;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOneWayRadioButton() {
        oneWayRadioButton.click();
    }

    public void enterFromField(String fromCity) {
        fromField.clear();
        fromField.sendKeys(fromCity);
    }

    public void enterDestinationField(String toCity) {
        toField.clear();
        toField.sendKeys(toCity);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void clickCalendarIcon() {
        calendarIcon.click();
    }

    public void chooseDefaultDate() {
        clickCalendarIcon();
        selectedDate.click();
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

    public boolean searchSummaryDisplayed() {
        return TestUtils.isElementDisplayed(searchSummary, 20);
    }

    public List<WebElement> getAutoCompleteList(String flag) {
        List<WebElement> options = null;
        if (flag.equals("from")) {
            options = driver.findElement(By.id("ui-id-1"))
                    .findElements(By.tagName("li"));
        } else if (flag.equals("to")) {
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
        while (iteratorObj.hasNext()) {
            element = iteratorObj.next();
            if (element != null) {
                if (element.getText().contains(searchString)) {
                    return element;
                } else {
                    System.out.println("Problem with input. No suggestions for the string '"
                            + searchString + "'. Returning null.");
                }
            }
        }
        return element;
    }
}
