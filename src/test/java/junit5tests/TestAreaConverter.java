package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAreaConverter {
    private final Calculator c = new Calculator();


    @Test
    public void testSquareMeterToSquareKilometer() {
        assertEquals(1, c.convert("m^2", "km^2", 1000000), 0);
    }
}
