package eu.senla.shabalin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public int getDynamicContentCount() {
        int count = 0;
        List<WebElement> list = elementList;
        clickHereLink.click();
        List<WebElement> newElementList = driver.findElements(By.cssSelector(dynamicRowLocator));
        for(int i = 0; i < elementList.size(); i++) {
            if(elementList.get(i).findElement(By.tagName("img")).getAttribute("src")
                    .equals(newElementList.get(i).findElement(By.tagName("img")).getAttribute("src")) &&
                    elementList.get(i).findElement(By.className("large-10")).getText()
                            .equals(newElementList.get(i).findElement(By.className("large-10")).getText())) {


                System.out.println(elementList.get(i).findElement(By.tagName("img")).getAttribute("src"));
                System.out.println(newElementList.get(i).findElement(By.tagName("img")).getAttribute("src"));
                System.out.println(elementList.get(i).findElement(By.className("large-10")).getText());
                System.out.println(newElementList.get(i).findElement(By.className("large-10")).getText());

                count += 1;
            }
        }

        return count;
    }
}
