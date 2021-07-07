import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.LoginPage;
import eu.senla.shabalin.pageobjects.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllTest extends DataFixture {
    private LoginPage loginPage;
    private final String correctLogin = property.getProperty("correctLogin");
    private final String incorrectLogin = property.getProperty("incorrectLogin");
    private final String correctPassword = property.getProperty("correctPassword");
    private final String incorrectPassword = property.getProperty("incorrectPassword");
    private final String loginPageUrl = property.getProperty("loginPageUrl");
    private final String mainPageUrl = property.getProperty("mainPageUrl");
    private SoftAssertions assertions = new SoftAssertions();
    private MainPage mainPage;

    private void assertUrlAndAlertMessage(String pageUrl, String alertMessage) {
        assertions.assertThat(driver.getCurrentUrl()).as("check url").isEqualTo(pageUrl);
        assertions.assertThat(loginPage.getAlertMessage()).as("check message").isEqualTo(alertMessage);
        assertions.assertAll();
    }

    @BeforeEach
    public void beforeTest() {
        driver.get(loginPageUrl);
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
