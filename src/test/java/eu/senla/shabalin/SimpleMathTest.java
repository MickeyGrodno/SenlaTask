package eu.senla.shabalin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMathTest {
    private final int firstNumber = Integer.parseInt(System.getProperty("FIRST_NUMBER"));
    private final int secondNumber = Integer.parseInt(System.getProperty("SECOND_NUMBER"));
    private final String message = System.getProperty("MESSAGE");
    private final String testResult = System.getProperty("TEST_RESULT");

    @Test
    public void sumExampleTest() {
        int result = firstNumber+secondNumber;
        Assertions.assertTrue(result>0);
    }
    @Test
    public void minusExampleTest() {
        int result = firstNumber-secondNumber;
        Assertions.assertTrue(result>=0);
    }
    @Test
    public void wordCheckForMessage() {
        assertEquals("message", message.toLowerCase());
    }

    @Test
    public void choiceTestResultTest() {
        assertEquals("successful", testResult);
    }
}
