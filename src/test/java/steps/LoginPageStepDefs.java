package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPageStepDefs {

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
    public void press(String button)
    {
        $(By.xpath("//i [text()=' "+button+"']")).click();
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
