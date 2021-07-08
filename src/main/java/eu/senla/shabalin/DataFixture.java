package eu.senla.shabalin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DataFixture {
    protected static WebDriver driver;
    protected static Properties property;
    protected static ChromeOptions options;

    @BeforeAll
    public static void beforeAllTest() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--no-sandbox");
        options.setHeadless(true);
        property = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        } catch (IOException e) {
            System.err.println("Property file don't found!");
        }
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}
