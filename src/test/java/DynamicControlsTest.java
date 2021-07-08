import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.DynamicControls;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DynamicControlsTest extends DataFixture {
    private DynamicControls controls = new DynamicControls(driver);

    @BeforeEach
    public void beforeTest() {
        driver.get(property.getProperty("dynamicControlsUrl"));
    }

    @Disabled
    @Test
    public void isCheckboxHasBeenDeletedTest() {
        assertFalse(controls
                .clickToRemoveOrAddCheckboxButton()
                .isCheckboxPresent());
    }
    @Disabled
    @Test
    public void isCheckboxHasBeenAddedTest() {
        assertTrue(controls
                .clickToRemoveOrAddCheckboxButton()
                .clickToRemoveOrAddCheckboxButton()
                .isCheckboxPresent());
    }
    @Disabled
    @Test
    public void isCheckboxHasBeenSelectedTest() {
        assertTrue(controls.clickCheckbox().isCheckboxSelected());
    }
    @Disabled
    @Test
    public void isCheckboxHasBeenNotSelectedTest() {
        assertFalse(controls.clickCheckbox().clickCheckbox().isCheckboxSelected());
    }
    @Disabled
    @Test
    public void isTextFieldClickable() {
        assertTrue(controls.clickEnableOrDisableTextFieldButton().isTextFieldClickable());
    }
    @Disabled
    @Test
    public void isTextFieldNotClickable() {
        assertFalse(controls.clickEnableOrDisableTextFieldButton()
                .clickEnableOrDisableTextFieldButton()
                .isTextFieldClickable());
    }

}
