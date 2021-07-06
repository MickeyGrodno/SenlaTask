package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage implements PageObject{
    private WebDriver driver;
    private final By USERNAME_FIELD = By.id("username");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.className("radius");
    private final By ALERT_MESSAGE = By.id("flash");
    private List<WebElement> elements;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    public PageObject loginWithUserCredentials(String userName, String userPassword) {
        driver.findElement(USERNAME_FIELD).sendKeys(userName);
        driver.findElement(PASSWORD_FIELD).sendKeys(userPassword);
        driver.findElement(LOGIN_BUTTON).click();

        if (driver.getCurrentUrl().equals("http://the-internet.herokuapp.com/login")) {
            return this;
        } else {
            return new MainPage(driver);
        }
    }

    public String getAlertMessage() {
        elements = driver.findElements(ALERT_MESSAGE);
        if(elements.size() == 1) {
            return elements.get(0).getText();
        } else {
            return null;
        }
    }
}
