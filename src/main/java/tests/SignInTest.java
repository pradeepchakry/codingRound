package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignInPage;
import utils.Commons;
import utils.TestBase;

public class SignInTest {

    WebDriver driver;
    SignInPage page;

    @BeforeClass
    public void setup() {
        TestBase.initialize();
        this.driver = TestBase.driver;
        page = PageFactory.initElements(driver, SignInPage.class);
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        driver.get("https://www.cleartrip.com/");
        Commons.waitFor(4000);

        //Go to Your Trips
        page.clickYourTripsLink();
        Commons.waitFor(4000);

        //Click on Sign In button on Page
        page.clickSignInButtonOnPage();
        Commons.waitFor(4000);

        //Click on Sign In button on iFrame without supplying the credentials
        page.clickSignInButtonOniFrame();
        Commons.waitFor(4000);

        //Assert the error messages displayed
        Assert.assertTrue(page.getErrorMessage1().contains("There were errors in your submission"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
