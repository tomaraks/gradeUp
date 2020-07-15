package pages.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private By previousPapers = By.cssSelector("label[for='toggle-tab-3']");
    private By startTests = By.cssSelector(".flex.justify-between.mt3 a.pysp-start-test");
    private By resumeTests = By.cssSelector(".flex.justify-between.mt3 a.pysp-resume-test");
    private By viewResults = By.cssSelector(".flex.justify-between.mt3 a.pysp-see-result");
    private By startTestsByViewResults = By.cssSelector("button.self-start.start-test-button");
    private By resumeTestsByViewResults = By.cssSelector("button.self-start.resume-test-button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToElement(By webElement) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(webElement));
    }

    public void clickOnPreviousPapers() {
        driver.findElement(previousPapers).click();
    }

    public TestPage startTest() {
        List<WebElement> webElementList = driver.findElements(startTests);
        List<WebElement> webElementList2 = driver.findElements(resumeTests);
        if(webElementList.size()>0) {
            webElementList.get(0).click();
            return new TestPage(driver);
        } else if(webElementList2.size()>0) {
            webElementList2.get(0).click();
            return new TestPage(driver);
        } else {
            driver.findElements(viewResults).get(0).click();
            WebDriverWait wait = new WebDriverWait(driver, 40);
            if(driver.findElements(startTestsByViewResults).size()>0) {
                scrollToElement(startTestsByViewResults);
                driver.findElements(startTestsByViewResults).get(0).click();
            } else {
                wait.until(ExpectedConditions.presenceOfElementLocated(resumeTestsByViewResults));
                scrollToElement(resumeTestsByViewResults);
                driver.findElements(resumeTestsByViewResults).get(0).click();
            }
            return new TestPage(driver);
        }
    }


}
