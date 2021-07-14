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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DataFixture {
    protected static WebDriver driver;
    protected static Properties property;
    protected static ChromeOptions options;
    protected static String loginPageUrl;

    @BeforeAll
    public static void beforeAllTest() throws IOException {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--no-sandbox");
        options.setHeadless(true);
        property = new Properties();
        property.load(new FileInputStream("src/main/resources/config.properties"));
        loginPageUrl = property.getProperty("loginPageUrl");
        String pathToThePropertyFile;
        try {
            if(System.getProperty("os.name").equals("Linux")) {
                pathToThePropertyFile = "src/main/resources/config.properties";
            } else {
                pathToThePropertyFile = "src\\main\\resources\\config.properties";
            }
            FileInputStream fis = new FileInputStream(pathToThePropertyFile);
            property.load(fis);
        } catch (IOException e) {
            System.err.println("Property file don't found!");
        }
    }
    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver(options);
        if(this.getClass().toString().equals("class eu.senla.shabalin.LoginPageTest")) {
            driver.get(loginPageUrl);
        }
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}
