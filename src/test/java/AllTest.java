import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.DynamicContent;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AllTest extends DataFixture {

    @Test
    public void dynamicContentIsPresentInPage() {
        driver.get(property.getProperty("dynamic.content.url"));
        DynamicContent content = new DynamicContent(driver);
        assertTrue(content.isDynamicContentPresent());
    }
}
