package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoginPage implements PageObject{
    private WebDriver driver;
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(className = "radius")
    private WebElement loginButton;
    @FindBy(id = "flash")
    private List<WebElement> elements;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageObject loginWithUserCredentials(String userName, String userPassword) {
        usernameField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        loginButton.click();

        if (driver.getCurrentUrl().equals("http://the-internet.herokuapp.com/login")) {
            return this;
        } else {
            return new MainPage(driver);
        }
    }

    public String getAlertMessage() {
        if(elements.size() == 1) {
            return elements.get(0).getText();
        } else {
            return null;
        }
    }
}
