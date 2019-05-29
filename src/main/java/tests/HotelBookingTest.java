package tests;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HotelsPage;
import utils.Commons;
import utils.TestBase;

public class HotelBookingTest {
    private HotelsPage page;

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        TestBase.initialize();
        this.driver = TestBase.driver;
        page = PageFactory.initElements(driver, HotelsPage.class);
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {
        driver.get("https://www.cleartrip.com/");

        //Go to Hotels page
        page.clickHotelsLink();

        //Enter locality
        page.enterLocality("Indiranagar, Bangalore");

        //focus away from all the fields to make a clear selection
        page.clickHeading();
        Commons.waitFor(2000);

        //choose dates that are selected by default
        page.chooseActiveCheckInDate();
        Commons.waitFor(2000);
        page.chooseActiveCheckOutDate();
        Commons.waitFor(2000);

        //All the required fields are filled. Search for Hotels
        page.clickSearchHotelsButton();
        Commons.waitFor(2000);

        Assert.assertTrue(page.isSearchResultDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
