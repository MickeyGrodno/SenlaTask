package eu.senla.shabalin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DataFixture {
    protected static WebDriver driver;
    protected String loginPageUrl = "http://the-internet.herokuapp.com/login";

    @BeforeAll
    public static void beforeAllTest() {
//        System.setProperty("webdriver.chrome.driver", "src/main/java/eu/senla/shabalin/driver/chromedriver91.0.4472.101");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--no-sandbox");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    public void beforeTest() {
        driver.get(loginPageUrl);

    }

    @AfterAll
    public static void afterAllTest() {
        driver.quit();
    }
}
