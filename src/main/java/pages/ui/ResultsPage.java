package pages.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;
    private By listOfAnswers = By.cssSelector("div.flex.performance-overview > ul > li");
    private By scores = By.cssSelector(".flex.result-card-height > div h1.green");
    private By cutoff = By.cssSelector(".flex.justify-center.bg-white > span");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public HashMap<String, Integer> getAllAnswers() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<WebElement> answersList = driver.findElements(listOfAnswers);
        for(WebElement webElement: answersList) {
            map.put(webElement.findElement(By.cssSelector(".fl")).getText().trim(), Integer.parseInt(webElement.findElement(By.cssSelector(".fr")).getText()));
        }
        return map;
    }

    public String getScore() {
        return driver.findElements(scores).get(0).getText();
    }

    public String getRank() {
        return driver.findElements(scores).get(1).getText();
    }

    public String getCutOff() {
        return driver.findElement(cutoff).getText();
    }
}
