package pages.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TestPage {
    private WebDriver driver;
    private By sections = By.cssSelector("div.absolute.flex.mt2 > div.flex.flex-column");
    private By questions = By.cssSelector("div.mb3.flex.justify-center");
    private By questionsColumn = By.cssSelector("div.flex.flex-column .pa3-l.pb0");
    private By questionsHeader = By.cssSelector("div.flex.fixed-width-container aside .fixed .absolute.flex.mt2 > .flex-l");
    private By options = By.cssSelector("ul li");
    private By submitButton = By.cssSelector("div.flex.items-center > button");
    private By submitButton1 = By.cssSelector(".flex.justify-end.items-center > a.bg-green");
    public static HashMap<String, Integer> score;

    public TestPage(WebDriver driver) {
        this.driver = driver;
    }

    public void executeTests() throws InterruptedException {
        score = new HashMap<>();
        WebElement header = driver.findElement(questionsHeader);
        List<WebElement> sectionList = driver.findElements(sections);

        JavascriptExecutor je = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, 40);
        int correctAnswers = 0;
        int wrongAnswers = 0;
        int unAttemptAnswers = 0;
        for (WebElement section : sectionList) {
            wait.until(ExpectedConditions.visibilityOf(section));
            int count2 = 0;
            while (!section.isDisplayed() && count2<3 && !section.isEnabled()) {
                Thread.sleep(2000);
                header.click();
                je.executeScript("arguments[0].scrollIntoView(true);", section);
                count2++;
            }
            List<WebElement> questionList = section.findElements(questions);
            for (WebElement question : questionList) {
                int count1 = 0;
                while (!question.isEnabled() && count1<5 && !question.isDisplayed()) {
                    Thread.sleep(2000);
                    header.click();
                    actions.sendKeys(Keys.ARROW_DOWN).build().perform();
                    actions.moveToElement(question).build().perform();
                    je.executeScript("arguments[0].scrollIntoView(true);", question);
                    count1++;
                }
                try {
                    wait.until(ExpectedConditions.visibilityOf(question));
                    question.click();
                } catch (Exception ex) {
                    je.executeScript("arguments[0].scrollIntoView(true);", question);
                    try {
                        question.click();
                    } catch (ElementClickInterceptedException ex1) {
                        header.click();
                        actions.sendKeys(Keys.ARROW_UP).build().perform();
                        actions.sendKeys(Keys.ARROW_UP).build().perform();
                        wait.until(ExpectedConditions.elementToBeClickable(question));
                        actions.moveToElement(question).click().build().perform();
                    }
                }
                Random rand = new Random();
                int rand_int1 = rand.nextInt(4);
                selectAnswer(rand_int1, count);
                count++;
            }

            String correct = section.getText().substring(section.getText().lastIndexOf("Correct(") + 8, section.getText().indexOf(")\n" + "Wrong"));
            String wrong = section.getText().substring(section.getText().indexOf("Wrong(") + 6, section.getText().indexOf(")\n" + "Unattempted"));
            String unattempt = section.getText().substring(section.getText().indexOf("Unattempted(") + 12, section.getText().lastIndexOf(")"));
            correctAnswers = correctAnswers + Integer.parseInt(correct);
            wrongAnswers = wrongAnswers + Integer.parseInt(wrong);
            unAttemptAnswers = unAttemptAnswers + Integer.parseInt(unattempt);
            score.put("Correct", correctAnswers);
            score.put("Incorrect", wrongAnswers);
            score.put("Unattempted", unAttemptAnswers);
        }
    }

    public ResultsPage submitTests() throws InterruptedException {
        driver.findElement(submitButton).click();
        Thread.sleep(2000);
        driver.findElement(submitButton1).click();
        return new ResultsPage(driver);
    }

    private void selectAnswer(int option, int question) throws InterruptedException {
        List<WebElement> questionColumns = driver.findElements(questionsColumn);
        List<WebElement> optionsOpt = questionColumns.get(question).findElements(options);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        int count = 0;
        while (!optionsOpt.get(option).isDisplayed() && count < 3) {
            optionsOpt.get(0).click();
            je.executeScript("arguments[0].scrollIntoView(true);", optionsOpt.get(option));
            count++;
        }
        try {
            optionsOpt.get(option).click();
        } catch (ElementClickInterceptedException ex) {
            optionsOpt.get(option).click();
        }
        Thread.sleep(2000);
    }
}
