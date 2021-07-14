package eu.senla.shabalin;

import eu.senla.shabalin.pageobjects.LoginPage;
import eu.senla.shabalin.pageobjects.MainPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @Attachment
    private byte[] takeScreenShot() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(
                    new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),
                    "png",
                    baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (AWTException | IOException e) {
            System.err.println("Failed to create or save screenshot");
        }
        return null;
    }

    @Attachment(value = "Вложение")
    private byte[] getExpectedParametersOnTxt(String pageUrl, String alertMessage) {
        List<String> list = Arrays.asList(pageUrl, alertMessage);
        String fileDirectory;
        if(System.getProperty("os.name").equals("Linux")) {
            fileDirectory = "src/main/resources/testreportdata/";
        } else {
            fileDirectory = "src\\main\\resources\\testreportdata\\";
        }
        Path file = Paths.get(fileDirectory + LocalDateTime.now());
        try {
            Files.write(file, list, StandardCharsets.UTF_8);
            return Files.readAllBytes(file);
        } catch (IOException e) {
            System.err.println("File not found!");
        }
        return null;
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
