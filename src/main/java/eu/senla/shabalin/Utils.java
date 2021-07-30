package eu.senla.shabalin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.TakeScreenshot;
import org.openqa.selenium.TakesScreenshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getCurrentDateTime() {
        DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date date = new Date();
        return customFormat.format(date);
    }

    public static String getScreenshotPath() {
        String sys = System.getProperty("user.dir");
        String screenShot = Selenide.screenshot(Utils.getCurrentDateTime())
                .replace("file:/", "");
        System.out.println();
        return screenShot;
    }
}
