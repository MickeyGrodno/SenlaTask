package eu.senla.shabalin;

import com.aventstack.extentreports.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.IOException;

public class CustomTestWatcher implements TestWatcher {
    private ExtentReports report;

    public CustomTestWatcher(ExtentReports reports) {
        this.report = reports;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {

        ExtentTest logger = report.createTest(context.getDisplayName(), "Test Success");
        logger.info("Start "+context.getDisplayName());
        logger.log(Status.PASS, "Success");
        report.flush();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ExtentTest logger = report.createTest(context.getDisplayName(), "Test Failed");
        logger.info("Start "+context.getDisplayName());
//        String path = Utils.getScreenshotPath();
        logger.fail("Test FAILED!");
//        try {
//            logger.fail("Test FAILED!", MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
//        } catch (IOException e) {
//            System.err.println("File not found!");
//        }
        report.flush();
    }
}
