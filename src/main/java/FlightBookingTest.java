import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

public class FlightBookingTest {

    WebDriver driver;
    private String originString;
    private String destinationString;

    @BeforeClass
    public void setup() {
        setDriverPath();
        driver = new ChromeDriver();
    }


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        originString = "Bangalore";
        destinationString = "Delhi";
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys(originString);


        //wait for the auto complete options to appear for the origin

        waitFor(6000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        WebElement originOption = getNameBySuggestion(originOptions, originString);
        originOption.click();


        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys(destinationString);


        //wait for the auto complete options to appear for the destination

        waitFor(6000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));

        WebElement destinationOption = getNameBySuggestion(destinationOptions, destinationString);
        destinationOption.click();

        driver.findElement(By.xpath("//*[@class='calendarIcon']/i")).click();

        waitFor(3000);

        driver.findElement(By.xpath("//td[contains(@class, 'selected')]")).click();

        //*[@id="ui-datepicker-div"]/div[1]/table/tbody/tr[2]/td[4]/a

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
    }


    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.out.println("windows...");
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

    public WebElement getNameBySuggestion(List<WebElement> elementList, String searchString) {
        WebElement element = null;
        Iterator<WebElement> iteratorObj = elementList.iterator();
        while(iteratorObj.hasNext()) {
            element = (WebElement) iteratorObj.next();
            if(element != null) {
                if(element.getText().contains(searchString)) {
                    return element;
                } else {
                    System.out.println("Problem with data. No such element that starts with " + searchString + " in the list. Returning null.");
                }
            }
        }
        return element;
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
