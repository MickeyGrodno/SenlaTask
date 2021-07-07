import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.DynamicContent;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DynamicContentTest extends DataFixture {

    @Test
    public void dynamicContentIsPresentInPageTest() {
        driver.get(property.getProperty("dynamic.content.url"));
        DynamicContent content = new DynamicContent(driver);
        assertTrue(content.isDynamicContentPresent());
    }
}
