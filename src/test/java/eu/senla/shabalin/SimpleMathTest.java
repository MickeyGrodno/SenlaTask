package eu.senla.shabalin;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.engine.descriptor.DynamicExtensionContext;
import org.junit.platform.engine.TestExecutionResult;
import org.opentest4j.AssertionFailedError;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMathTest extends Fixture{
//    private final int firstNumber = Integer.parseInt(System.getProperty("FIRST_NUMBER"));
//    private final int secondNumber = Integer.parseInt(System.getProperty("SECOND_NUMBER"));
//    private final String message = System.getProperty("MESSAGE");
//    private final String testResult = System.getProperty("TEST_RESULT");

    private final int firstNumber = 1;
    private final int secondNumber = 2;
    private final String message = "message";
    private final String testResult = "res";
    private static ExtentTest logger;

    @Test
    public void sumExampleTest() {
        int result = firstNumber + secondNumber;
        Assertions.assertTrue(result > 0);
    }

    @Test
    public void minusExampleTest() {
        int result = firstNumber-secondNumber;
        Assertions.assertTrue(result>=0);
    }
    @Test
    public void wordCheckForMessage() {
        assertEquals("message", message.toLowerCase());
    }

    @Test
    public void choiceTestResultTest() {
        assertEquals("successful", testResult);
    }

//    @AfterAll
//    public static void afterAllTest() {
//        AfterTestExecutionCallback callback = new AfterTestExecutionCallback() {
//        }
//        if(new TestExecutionResult.Status().)
//        report.flush();
//    }
}
