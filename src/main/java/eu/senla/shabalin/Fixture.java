package eu.senla.shabalin;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;

public class Fixture {
    protected static ExtentReports report;

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
}
