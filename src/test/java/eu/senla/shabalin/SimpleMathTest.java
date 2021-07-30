package eu.senla.shabalin;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.File;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMathTest {
    private final int firstNumber = Integer.parseInt(System.getProperty("FIRST_NUMBER"));
    private final int secondNumber = Integer.parseInt(System.getProperty("SECOND_NUMBER"));
    private final String message = System.getProperty("MESSAGE");
    private final String testResult = System.getProperty("TEST_RESULT");

//    private final int firstNumber = 1;
//    private final int secondNumber = 2;
//    private final String message = "message";
//    private final String testResult = "res";
    private static ExtentReports report;
    private static ExtentTest logger;

    @BeforeAll
    public static void beforeAllTest() {
        String directory = System.getProperty("user.dir")+"/extent-report/";
        File fileDirectory = new File(directory);
        if(!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }
        ExtentHtmlReporter extent = new ExtentHtmlReporter(
                new File(System.getProperty("user.dir")+"/extent-report/extHtmlRep.html"));
        report = new ExtentReports();
        report.attachReporter(extent);
    }

    @Test
    public void sumExampleTest() {
        try {
            logger = report.createTest("Sum example");
            logger.info("Start sum example test");
            int result = firstNumber + secondNumber;
            Assertions.assertTrue(result > 0);
            logger.pass("Test passed");
        } catch (AssertionFailedError e) {
            logger.fail("Test failed");
            e.printStackTrace();
        }
    }
    @Test
    public void minusExampleTest() {
        logger = report.createTest("Minus example");
        int result = firstNumber-secondNumber;
        Assertions.assertTrue(result>=0);
    }
    @Test
    public void wordCheckForMessage() {
        logger = report.createTest("Checking a message for a word 'message'");
        assertEquals("message", message.toLowerCase());
    }

    @Test
    public void choiceTestResultTest() {
        logger = report.createTest("Manual selection of test result");
        assertEquals("successful", testResult);
    }

    @AfterAll
    public static void afterAllTest() {
        report.flush();
    }
}
