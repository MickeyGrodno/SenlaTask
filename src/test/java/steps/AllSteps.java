package steps;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AllSteps {
    List<String> contentFromPageList = new ArrayList<>();

    @Before
    public void browserSettings() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @Given("^open \"([^\"]*)\"")
    public void openLoginPage(String url)
    {
        open(url);
    }

    @And("^type to input with name \"([^\"]*)\" text: \"([^\"]*)\"$")
    public void typeToInputWithNameText(String input, String text)
    {
        $(byId(input.toLowerCase())).sendKeys(text);
    }

    @And("^press button with text \"([^\"]*)\"$")
    public void pressButton(String button)
    {
        $(By.xpath("//i [text()=' "+button+"']")).click();
    }

    @And("^received content with characters and text")
    public void getCharacterAndTextContentFromPage() {
        List<SelenideElement> elements = $$("div.large-centered > div.row");
        elements.forEach(a -> {
            contentFromPageList.add(a.$(By.tagName("img")).getAttribute("src"));
            contentFromPageList.add(a.$(By.className("large-10")).getText()); }
        );
    }

    @And("^click to link with text \"([^\"]*)\"$")
    public void clickLinkWithText(String linkText) {
        $(withText(linkText)).click();
    }

    @Then("^verify that content before button click is different from content after button click")
    public void verifyThatContentDifferent() {
        boolean isDifferent = false;
        for(int i = 0; i < contentFromPageList.size()/2; i++) {
            if(!contentFromPageList.get(i).equals(i+contentFromPageList.get(contentFromPageList.size()/2))) {
                isDifferent = true;
                break;
            }
        }
        contentFromPageList.clear();
        assertTrue(isDifferent);
    }

    @Then("^element with text \"([^\"]*)\" should exist")
    public void elementWithTextShouldExist(String elementText)
    {
        assertTrue($$(withText(elementText)).size() == 1);

    }

    @Then("^verify that page with url \"([^\"]*)\" is opened and element with text \"([^\"]*)\" should exist$")
    public void verifyThatPageWithUrlIsOpened(String verifyUrl, String elementText)
    {
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(url()).as("Check url").isEqualTo(verifyUrl);
        assertions.assertThat($$(withText(elementText)).size())
                .as("Checking for the presence of an element").isEqualTo(1);
    }
}
