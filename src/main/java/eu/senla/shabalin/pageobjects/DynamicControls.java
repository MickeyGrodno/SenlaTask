package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class DynamicControls implements PageObject{
    private WebDriver driver;
    @FindBy(css = "form#checkbox-example>button")
    private WebElement removeOrAddCheckboxButton;
    @FindBy(id = "loading")
    private WebElement loadingLine;
    @FindBy(css = "form#input-example>button")
    private WebElement enableOrDisableButton;
    @FindBy(css = "form#input-example>input")
    private WebElement textField;
    private By checkBox = By.xpath("//input[@type='checkbox']");

    public boolean isCheckboxPresent() {
        return driver.findElements(checkBox).size() > 0;
    }

    public DynamicControls(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DynamicControls clickToRemoveOrAddCheckboxButton() {
        if(isCheckboxPresent()) {
            removeOrAddCheckboxButton.click();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.stalenessOf(driver.findElement(checkBox)));
        } else {
            removeOrAddCheckboxButton.click();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(checkBox));
        }
        return this;
    }

    public boolean isCheckboxSelected() {
        return driver.findElement(checkBox).isSelected();
    }

    public DynamicControls clickCheckbox() {
        driver.findElement(checkBox).click();
        return this;
    }

    public DynamicControls clickEnableOrDisableTextFieldButton() {
        if(textField.isEnabled()) {
            enableOrDisableButton.click();
            new WebDriverWait(driver, 10)
                    .until(not(ExpectedConditions.elementToBeClickable(textField)));
        } else {
            enableOrDisableButton.click();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(textField));
        }
        return this;
    }

    public boolean isTextFieldClickable() {
        return textField.isEnabled();
    }
}
