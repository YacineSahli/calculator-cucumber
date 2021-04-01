package junit5tests.convertorTests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPressureConverter {
    private final Calculator c = new Calculator();

    @Test
    public void testPascalToPsi() {
        assertEquals(0.2091444, c.convert("Pa", "psi", 1442),  0.0001);
    }

    @Test
    public void testFeetPerSecondToKilometersPerHour() {
        assertEquals(2.045165, c.convert("mmHg", "bar", 1534),  0.001);
    }
}
