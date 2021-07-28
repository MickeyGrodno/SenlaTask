package eu.senla.shabalin;

import eu.senla.shabalin.pageobjects.LoginPage;
import eu.senla.shabalin.pageobjects.MainPage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static eu.senla.shabalin.Utils.getExpectedParametersOnTxt;
import static eu.senla.shabalin.Utils.takeScreenShot;

@Disabled
public class LoginPageTest extends DataFixture {
    private LoginPage loginPage;
    private final String correctLogin = property.getProperty("correctLogin");
    private final String incorrectLogin = property.getProperty("incorrectLogin");
    private final String correctPassword = property.getProperty("correctPassword");
    private final String incorrectPassword = property.getProperty("incorrectPassword");
    private final String loginPageUrl = property.getProperty("loginPageUrl");
    private final String mainPageUrl = property.getProperty("mainPageUrl");
    private SoftAssertions assertions = new SoftAssertions();
    private MainPage mainPage;

    @Step("Checking for the presence of the Url '{pageUrl}' page and alert message 'alertMessage' ")
    private void assertUrlAndAlertMessage(String pageUrl, String alertMessage){
        getExpectedParametersOnTxt(pageUrl, alertMessage);
        takeScreenShot();
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(pageUrl);
        assertions.assertThat(loginPage.getAlertMessage()).as("check message").isEqualTo(alertMessage);
        assertions.assertAll();
    }

    @Test
    public void loginWithIncorrectLoginAndIncorrectPassword() {
        loginPage = new LoginPage(driver);
        loginPage.loginWithUserCredentials(incorrectLogin, incorrectPassword);
        assertUrlAndAlertMessage(loginPageUrl, "Your username is invalid!\n×");
    }

    @Test
    public void loginWithCorrectLoginAndIncorrectPassword() {
        loginPage = new LoginPage(driver);
        loginPage.loginWithUserCredentials(correctLogin, incorrectPassword);
        assertUrlAndAlertMessage(loginPageUrl, "Your password is invalid!\n×");
    }

    @Test
    public void loginWithCorrectLoginAndCorrectPassword() {
        loginPage = new LoginPage(driver);
        mainPage = (MainPage) loginPage.loginWithUserCredentials(correctLogin, correctPassword);
        assertUrlAndAlertMessage(mainPageUrl, "You logged into a secure area!\n×");
    }

    @Test
    public void SuccessLogoutFromMainPage() {
        loginPage = new LoginPage(driver);
        mainPage = (MainPage) loginPage.loginWithUserCredentials(correctLogin, correctPassword);
        loginPage = (LoginPage) mainPage.logoutFromPage();
        assertUrlAndAlertMessage(loginPageUrl, "You logged out of the secure area!\n×");
    }
}
