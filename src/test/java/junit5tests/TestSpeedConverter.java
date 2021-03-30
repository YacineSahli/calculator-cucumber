package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSpeedConverter {
    private Calculator c = new Calculator();

    @Test
    public void testMeterPerSecondToKnots() {
        assertEquals(19.4384, c.convert("mps", "kts", 10),  0.001);
    }

    @Test
    public void testFeetPerSecondToKilometersPerHour() {
        assertEquals(57.0586, c.convert("ftps", "kph", 52),  0.0001);
    }
}
