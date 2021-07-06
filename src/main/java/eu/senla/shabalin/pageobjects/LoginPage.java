package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage implements PageObject{
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.className("radius");
    private By alertMessage = By.id("flash");
    private List<WebElement> elements;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    public PageObject loginWithUserCredentials(String userName, String userPassword) {
        driver.findElement(usernameField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(userPassword);
        driver.findElement(loginButton).click();

        if (driver.getCurrentUrl().equals("http://the-internet.herokuapp.com/login")) {
            return this;
        } else {
            return new MainPage(driver);
        }
    }

    public String getAlertMessage() {
        elements = driver.findElements(alertMessage);
        if(elements.size() == 1) {
            return elements.get(0).getText();
        } else {
            return null;
        }
    }
}
