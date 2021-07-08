package eu.senla.shabalin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DataFixture {
    protected static WebDriver driver;
    protected String loginPageUrl = "http://the-internet.herokuapp.com/login";
    protected static ChromeOptions options;

    @BeforeAll
    public static void beforeAllTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/eu/senla/shabalin/driver/chromedriver91.0.4472.101");
        options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--no-sandbox");
        options.setHeadless(true);
    }

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver(options);
        driver.get(loginPageUrl);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

}
