package eu.senla.shabalin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataFixture {
    protected static WebDriver driver;
    protected static Properties property;
    protected static ChromeOptions options;
    protected static String loginPageUrl;
    protected static String dynamicControlsUrl;
    protected static String dynamicContentUrl;


    @BeforeAll
    public static void beforeAllTest() throws IOException {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--no-sandbox");
        options.setHeadless(true);
        String pathToThePropertyFile;
        try {
            if(System.getProperty("os.name").equals("Linux")) {
                pathToThePropertyFile = "src/main/resources/config.properties";
            } else {
                pathToThePropertyFile = "src\\main\\resources\\config.properties";
            }
            property = new Properties();
            property.load(new FileInputStream(pathToThePropertyFile));
        } catch (IOException e) {
            System.err.println("Property file don't found!");
        }
        loginPageUrl = property.getProperty("loginPageUrl");
        dynamicControlsUrl = property.getProperty("dynamicControlsUrl");
        dynamicContentUrl = property.getProperty("dynamicContentUrl");
    }
    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver(options);
        if(this.getClass().toString().equals("class eu.senla.shabalin.LoginPageTest")) {
            driver.get(loginPageUrl);
        } else {
            if (this.getClass().toString().equals("class eu.senla.shabalin.DynamicControlsTest")) {
                driver.get(dynamicControlsUrl);
            } else {
                if(this.getClass().toString().equals("class eu.senla.shabalin.DynamicContentTest")) {
                    driver.get(dynamicContentUrl);
                }
            }
        }
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}
