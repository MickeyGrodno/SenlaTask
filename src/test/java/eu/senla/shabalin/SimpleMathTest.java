package eu.senla.shabalin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {
    private final int firstNumber = Integer.parseInt(System.getProperty("FIRST_NUMBER"));
    private final int secondNumber = Integer.parseInt(System.getProperty("SECOND_NUMBER"));

    @Test
    public void sumExampleTest() {
        System.out.println(firstNumber);
        System.out.println(secondNumber);
        int result = firstNumber+secondNumber;
        Assertions.assertEquals(0, result);
    }
}
