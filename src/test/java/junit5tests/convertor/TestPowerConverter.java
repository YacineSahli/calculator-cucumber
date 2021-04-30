package junit5tests.convertor;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPowerConverter {
    private final Calculator c = new Calculator();

    @Test
    public void testWattToHorsepower() {
        assertEquals(68.705926, c.convert("W", "hp", 51234), 0.001);
    }

    @Test
    public void testTonOfRefrigerationToWatts() {
        assertEquals(8088.76, c.convert("TR", "W", 2.3), 0.1);
    }
}
