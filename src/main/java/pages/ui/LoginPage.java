package pages.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class LoginPage {
    private WebDriver driver;
    private By signInGoogle = By.cssSelector("button[data-dname=\"continue-with-google\"]");
    private By emailID = By.cssSelector("input[type='email']");
    private By nextButton = By.cssSelector("#identifierNext > div > button > span");
    private By passwordID = By.cssSelector("input[type='password']");
    private By nextPasswordButton = By.cssSelector("#passwordNext > div > button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public void navigateToNewCityPage(String cityName) {
        driver.get("https://www.bizjournals.com/" + cityName + "/feature/crane-watch");
    }

    public HomePage signInWithGoogle(String username, String password) throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(signInGoogle).click();
        Set<String> handles = driver.getWindowHandles();
        for(String handle: handles) {
            driver.switchTo().window(handle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfElementLocated(emailID));
        driver.findElement(emailID).sendKeys(username);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(passwordID)));
        driver.findElement(passwordID).sendKeys(password);
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        //driver.close();
        driver.switchTo().window(winHandleBefore);
        return new HomePage(driver);
    }


}

