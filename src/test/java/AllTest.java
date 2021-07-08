import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.LoginPage;
import eu.senla.shabalin.pageobjects.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllTest extends DataFixture {
    private String correctLogin = "tomsmith";
    private String incorrectLogin = "tomsmith123";
    private String correctPassword = "SuperSecretPassword!";
    private String incorrectPassword = "NotSuperSecretPassword!";
    private String loginPageUrl = "http://the-internet.herokuapp.com/login";
    private String mainPageUrl = "http://the-internet.herokuapp.com/secure";
    private SoftAssertions assertions = new SoftAssertions();
    private LoginPage loginPage;
    private MainPage mainPage;

    private void assertUrlAndAlertMessage(String pageUrl, String alertMessage) {
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(pageUrl);
        assertions.assertThat(loginPage.getAlertMessage()).as("check message").isEqualTo(alertMessage);
        assertions.assertAll();
    }

    @Test
    public void loginWithIncorrectLoginAndIncorrectPasswordTest() {
        loginPage = new LoginPage(driver);
        loginPage.loginWithUserCredentials(incorrectLogin, incorrectPassword);
        assertUrlAndAlertMessage(loginPageUrl, "Your username is invalid!\n×");
    }

    @Test
    public void loginWithCorrectLoginAndIncorrectPasswordTest() {
        loginPage = new LoginPage(driver);
        loginPage.loginWithUserCredentials(correctLogin, incorrectPassword);
        assertUrlAndAlertMessage(loginPageUrl, "Your password is invalid!\n×");
    }

    @Test
    public void loginWithCorrectLoginAndCorrectPasswordTest() {
        loginPage = new LoginPage(driver);
        mainPage = (MainPage) loginPage.loginWithUserCredentials(correctLogin, correctPassword);
        assertUrlAndAlertMessage(mainPageUrl, "You logged into a secure area!\n×");
    }

    @Test
    public void SuccessLogoutFromMainPageTest() {
        loginPage = new LoginPage(driver);
        mainPage = (MainPage) loginPage.loginWithUserCredentials(correctLogin, correctPassword);
        loginPage = (LoginPage) mainPage.logoutFromPage();
        assertUrlAndAlertMessage(loginPageUrl, "You logged out of the secure area!\n×");
    }
}
