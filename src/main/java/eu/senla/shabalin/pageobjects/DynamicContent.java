package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DynamicContent implements PageObject{
    private WebDriver driver;
    private final String dynamicRowLocator = "div.large-centered > div.row";
    @FindBy(linkText = "click here")
    private WebElement clickHereLink;
    @FindBy(css = dynamicRowLocator)
    private List<WebElement> elementList;

    public DynamicContent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public boolean isDynamicContentPresent() {
        boolean dynamicContentIsPresent = false;

        List<String> oldElementsImgLinkAndText = new ArrayList<>();
        List<String> newElementsImgLinkAndText = new ArrayList<>();

        elementList.forEach(a -> {
            oldElementsImgLinkAndText.add(a.findElement(By.tagName("img")).getAttribute("src"));
            oldElementsImgLinkAndText.add(a.findElement(By.className("large-10")).getText()); }
        );

        clickHereLink.click();

        elementList.forEach(a -> {
            newElementsImgLinkAndText.add(a.findElement(By.tagName("img")).getAttribute("src"));
            newElementsImgLinkAndText.add(a.findElement(By.className("large-10")).getText()); }
        );

        for(int i = 0; i < oldElementsImgLinkAndText.size(); i++) {
            if(!oldElementsImgLinkAndText.get(i).equals(newElementsImgLinkAndText.get(i))) {
                dynamicContentIsPresent = true;
                break;
            }
        }
        return dynamicContentIsPresent;
    }
}
