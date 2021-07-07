import eu.senla.shabalin.DataFixture;
import eu.senla.shabalin.pageobjects.DynamicContent;
import org.junit.jupiter.api.Test;

public class AllTest extends DataFixture {

    @Test
    public void first() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dynamic_content");
        DynamicContent content = new DynamicContent(driver);
        System.out.println(content.getDynamicContentCount());
}
}
