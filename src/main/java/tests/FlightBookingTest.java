package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FlightsPage;
import utils.Commons;
import utils.TestBase;

public class FlightBookingTest {
    WebDriver driver;
    FlightsPage page;

    @BeforeClass
    public void setup() {
        TestBase.initialize();
        this.driver = TestBase.driver;
        page = PageFactory.initElements(driver, FlightsPage.class);
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        String originCity = "Bangalore";
        String destinationCity = "Delhi";

        driver.get("https://www.cleartrip.com/");
        Commons.waitFor(2000);

        //Choose One Way radio
        page.clickOneWayRadioButton();

        //Enter From and To cities
        page.fillFromCityField(originCity);
        page.fillToCityField(destinationCity);
        Commons.waitFor(6000);

        //Select date by clicking the calendar default
        page.chooseDefaultDate();
        Commons.waitFor(3000);

        //All fields filled in. Now click on search
        page.clickSearch();
        Commons.waitFor(5000);

        //Verify that result appears for the provided journey search
        Assert.assertTrue(page.searchSummaryDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
