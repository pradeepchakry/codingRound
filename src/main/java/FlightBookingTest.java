import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FlightsPage;
import utils.Commons;

public class FlightBookingTest {
    WebDriver driver;
    FlightsPage page;

    @BeforeClass
    public void setup() {
        TestBase.initialize();
        this.driver = TestBase.driver;
        page = new FlightsPage(driver);
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        String originCity = "Bangalore";
        String destinationCity = "Delhi";

        driver.get("https://www.cleartrip.com/");
        Commons.waitFor(2000);

        page.clickOneWayRadioButton();

        page.fillFromCityField(originCity);
        page.fillToCityField(destinationCity);

        Commons.waitFor(6000);

        //select the date by clicking the calendar default
        page.chooseDefaultDate();

        Commons.waitFor(3000);

        //all fields filled in. Now click on search
        page.clickSearch();

        Commons.waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
