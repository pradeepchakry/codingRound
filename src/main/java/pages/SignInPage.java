package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;

public class SignInPage {
    WebDriver driver;

    @FindBy(linkText = "Your trips")
    private WebElement yourTripsLink;

    @FindBy(id = "SignIn")
    private WebElement signInButtonOnPage;

    @FindBy(id = "signInButton")
    private WebElement signInButtonOnIFrame;

    @FindBy(xpath = "//*[@id='ContentFrame']/div/div/h1")
    private WebElement signInHeadingOniFrame;

    @FindBy(id = "errors1")
    private WebElement errorMessage1;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYourTripsLink() {
        yourTripsLink.click();
    }

    public void clickSignInButtonOnPage() {
        signInButtonOnPage.click();
    }

    public void clickSignInButtonOniFrame() {
        //Switch to Sign In iFrame
        driver.switchTo().frame("modal_window");

        //Click on Sign In button on confirmation
        if (TestUtils.isElementDisplayed(signInHeadingOniFrame, 20)) {
            signInButtonOnIFrame.click();
        }
    }

    public String getErrorMessage1() {
        return errorMessage1.getText();
    }
}
