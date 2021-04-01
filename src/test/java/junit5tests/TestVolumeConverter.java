package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVolumeConverter {
    private final Calculator c = new Calculator();

    @Test
    public void testCubicMeterToLiter() {
        assertEquals(1000, c.convert("m^3", "l", 1), 0);
    }

    @Test
    public void testCubicInchToCubicCentimeters() {
        assertEquals(16.3871, c.convert("in^3", "cm^3", 1), 0.0001);
    }

}
