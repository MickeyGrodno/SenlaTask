package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage implements PageObject{
    private WebDriver driver;
    @FindBy(className = "icon-signout")
    private WebElement logoutButton;
    @FindBy(id = "flash")
    private List<WebElement> alerts;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageObject logoutFromPage() {
        logoutButton.click();
        return new LoginPage(driver);
    }

    public String getAlertMessage() {
        if(alerts.size() == 1) {
            return alerts.get(0).getText();
        } else {
            return null;
        }
    }
}
