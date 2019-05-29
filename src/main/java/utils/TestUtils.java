package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {
    public static boolean isElementDisplayed(WebElement element, int durationInSecs) {
        boolean result = false;
        try {
            WebDriverWait wait = new WebDriverWait(TestBase.driver, durationInSecs);
            result = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return result;
    }
}
