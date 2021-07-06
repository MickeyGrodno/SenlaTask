package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage implements PageObject{
    private WebDriver driver;
    private final By LOGOUT_BUTTON = By.className("icon-signout");
    private final By ALERT_MESSAGE = By.id("flash");
    private List<WebElement> elements;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public PageObject logoutFromPage() {
        driver.findElement(LOGOUT_BUTTON).click();
        return new LoginPage(driver);
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
