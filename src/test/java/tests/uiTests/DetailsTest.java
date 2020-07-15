package tests.uiTests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ui.HomePage;
import pages.ui.ResultsPage;
import pages.ui.TestPage;
import utilities.ExcelWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsTest extends BaseTest {

    @Test
    public void executeFlowTest() throws InterruptedException {
        System.out.println(loginPage.getURL());
        Thread.sleep(5000);
        // Please enter user name and password
        HomePage homePage = loginPage.signInWithGoogle("akkilovesuridhi", "bpwL8Xps");
        Thread.sleep(15000);
        homePage.clickOnPreviousPapers();
        TestPage testPage = homePage.startTest();
        testPage.executeTests();

        ResultsPage resultsPage = testPage.submitTests();
        HashMap<String, Integer> answers = resultsPage.getAllAnswers();
        Assert.assertEquals(TestPage.score, answers);

        String score = resultsPage.getScore();
        String rank = resultsPage.getRank();
        String cutoff = resultsPage.getCutOff();

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Score");
        headers.add("Rank");
        headers.add("Cut Off");

        ArrayList<String> values = new ArrayList<>();
        values.add(score);
        values.add(rank);
        values.add(cutoff);

        ExcelWriter.writeScoresToExcel(headers, values);
    }
}
