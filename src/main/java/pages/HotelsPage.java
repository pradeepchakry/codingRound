package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.TestNGUtils;
import utils.Commons;
import utils.TestUtils;

public class HotelsPage {
    WebDriver driver;

    public HotelsPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(id = "CheckInDate")
    private WebElement checkInCaendar;

    @FindBy(id = "CheckOutDate")
    private WebElement checkOutCaendar;

    @FindBy(xpath = "//*[@id='Home']/section/div/div/div[1]/h1")
    private WebElement heading;

    @FindBy(className = "searchSummary")
    private WebElement searchSummary;

    @FindBy(xpath = "//td[contains(@class, 'selected')]")
    private WebElement selectedDate;

    public void clickHotelsLink() {
        hotelLink.click();
    }

    public void enterLocality(String locality) {
        localityTextBox.sendKeys(locality);
        Commons.waitFor(5000);

        //Choose the 1st result from suggestions
        localityTextBox.sendKeys(Keys.ARROW_DOWN);
        localityTextBox.sendKeys(Keys.ARROW_UP);
        localityTextBox.sendKeys(Keys.ENTER);
    }

    public void clickSearchHotelsButton() {
        searchButton.click();
    }

    public void selectDefaultTravellers() {
        new Select(travellerSelection)
                .selectByVisibleText("1 room, 2 adults");
    }

    public void clickCheckInCalendar() {
        checkInCaendar.click();
    }

    public void clickCheckOutCalendar() {
        checkOutCaendar.click();
    }

    public void chooseActiveCheckInDate() {
        clickCheckInCalendar();
        Commons.waitFor(2);
        selectedDate.click();
    }

    public void chooseActiveCheckOutDate() {
        clickCheckOutCalendar();
        Commons.waitFor(2);
        selectedDate.click();
    }

    public void clickHeading() {
        heading.click();
    }

    public boolean isSearchResultDisplayed() {
        return TestUtils.isElementDisplayed(searchSummary, 20);
    }
}
