package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage implements PageObject{
    private WebDriver driver;
    private By logoutButton = By.className("icon-signout");
    private By alertMessage = By.id("flash");
    private List<WebElement> elements;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public PageObject logoutFromPage() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
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
