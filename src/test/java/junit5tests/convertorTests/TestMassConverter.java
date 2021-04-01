package junit5tests.convertorTests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMassConverter {
    private Calculator c = new Calculator();


    @Test
    public void testTonToGrams() {
        assertEquals(1000000, c.convert("t", "g", 1),  0);
    }

    @Test
    public void testPoundsToOunzes() {
        assertEquals(16, c.convert("lb", "ounzes", 1),  0.0001);
    }
}
