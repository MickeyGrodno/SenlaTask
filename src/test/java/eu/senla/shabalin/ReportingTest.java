package eu.senla.shabalin;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportingTest {
    private static ExtentReports report;

    @RegisterExtension
    public CustomTestWatcher watcher = new CustomTestWatcher(report);

    @BeforeAll
    public static void beforeAll() {
        String directory = System.getProperty("user.dir")+"/extent-report/";
        File fileDirectory = new File(directory);
        if(!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }


        ExtentHtmlReporter extent = new ExtentHtmlReporter(
                new File(System.getProperty("user.dir")+"/extent-report/extHtmlRep"+Utils.getCurrentDateTime()+".html"));
        report = new ExtentReports();
        report.attachReporter(extent);
    }

    @Test
    public void exampleTest() {
        open("https://www.google.com/");
        assertEquals("https://www.google.co/", url());
    }
}
