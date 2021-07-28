package eu.senla.shabalin;

import eu.senla.shabalin.pageobjects.DynamicControls;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
@Disabled
public class DynamicControlsTest extends DataFixture {
    private DynamicControls controls;

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver(options);
        controls = new DynamicControls(driver);
        driver.get(property.getProperty("dynamicControlsUrl"));
    }

    @Test
    public void isCheckboxHasBeenDeletedTest() {
        assertFalse(controls
                .clickToRemoveOrAddCheckboxButton()
                .isCheckboxPresent());
    }
    @Test
    public void isCheckboxHasBeenAddedTest() {
        assertTrue(controls
                .clickToRemoveOrAddCheckboxButton()
                .clickToRemoveOrAddCheckboxButton()
                .isCheckboxPresent());
    }
    @Test
    public void isCheckboxHasBeenSelectedTest() {
        assertTrue(controls.clickCheckbox().isCheckboxSelected());
    }
    @Test
    public void isCheckboxHasBeenNotSelectedTest() {
        assertFalse(controls.clickCheckbox().clickCheckbox().isCheckboxSelected());
    }
    @Test
    public void isTextFieldClickable() {
        assertTrue(controls.clickEnableOrDisableTextFieldButton().isTextFieldClickable());
    }
    @Test
    public void isTextFieldNotClickable() {
        assertFalse(controls.clickEnableOrDisableTextFieldButton()
                .clickEnableOrDisableTextFieldButton()
                .isTextFieldClickable());
    }

}
