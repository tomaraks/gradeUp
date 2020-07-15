package tests.uiTests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ui.HomePage;
import pages.ui.ResultsPage;
import pages.ui.TestPage;

import java.util.HashMap;

public class DetailsTest extends BaseTest {

    @Test
    public void executeFlowTest() throws InterruptedException {
        System.out.println(loginPage.getURL());
        Thread.sleep(5000);
        HomePage homePage = loginPage.signInWithGoogle("akkilovesuridhi", "bpwL8Xps");
        Thread.sleep(15000);
        homePage.clickOnPreviousPapers();
        TestPage testPage = homePage.startTest();
        testPage.executeTests();
        System.out.println("Total is :- " + TestPage.score);

        ResultsPage resultsPage = testPage.submitTests();
        HashMap<String, Integer> answers = resultsPage.getAllAnswers();
        Assert.assertEquals(TestPage.score, answers);

        String score = resultsPage.getScore();
        String rank = resultsPage.getRank();
        String cutoff = resultsPage.getCutOff();
        System.out.println("Score " + score);
        System.out.println("Score " + rank);
        System.out.println("Score " + cutoff);
    }
}