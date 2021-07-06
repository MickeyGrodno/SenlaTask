import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.LoginPage;
import eu.senla.shabalin.pageobjects.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class AllTest extends DataFixture {
    private String correctLogin = "tomsmith";
    private String incorrectLogin = "tomsmith123";
    private String correctPassword = "SuperSecretPassword!";
    private String incorrectPassword = "NotSuperSecretPassword!";
    private String loginPageUrl = "http://the-internet.herokuapp.com/login";
    private String mainPageUrl = "http://the-internet.herokuapp.com/secure";
    SoftAssertions assertions = new SoftAssertions();
    private LoginPage loginPage;
    private MainPage mainPage;

    @Test
    public void loginWithIncorrectLoginAndIncorrectPassword() {
        loginPage = new LoginPage(driver);
        loginPage.loginWithUserCredentials(incorrectLogin, incorrectPassword);
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(loginPageUrl);
        assertions.assertThat(loginPage.getAlertMessage()).as("check message").isEqualTo("Your username is invalid!\n×");
        assertions.assertAll();
    }

    @Test
    public void loginWithCorrectLoginAndIncorrectPassword() {
        loginPage = new LoginPage(driver);
        loginPage.loginWithUserCredentials(correctLogin, incorrectPassword);
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(loginPageUrl);
        assertions.assertThat(loginPage.getAlertMessage()).as("check message").isEqualTo("Your password is invalid!\n×");
        assertions.assertAll();
    }

    @Test
    public void loginWithCorrectLoginAndCorrectPassword() {
        loginPage = new LoginPage(driver);
        mainPage = (MainPage) loginPage.loginWithUserCredentials(correctLogin, correctPassword);
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(mainPageUrl);
        assertions.assertThat(mainPage.getAlertMessage()).as("check message").isEqualTo("You logged into a secure area!\n×");
        assertions.assertAll();
    }

    @Test
    public void SuccessLogoutFromMainPage() {
        loginPage = new LoginPage(driver);
        mainPage = (MainPage) loginPage.loginWithUserCredentials(correctLogin, correctPassword);
        loginPage = (LoginPage) mainPage.logoutFromPage();
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(loginPageUrl);
        assertions.assertThat(loginPage.getAlertMessage()).as("check message").isEqualTo("You logged out of the secure area!\n×");
        assertions.assertAll();
    }
}
