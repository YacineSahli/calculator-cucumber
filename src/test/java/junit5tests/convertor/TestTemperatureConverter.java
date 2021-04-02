package junit5tests.convertor;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemperatureConverter {
    private Calculator c = new Calculator();

    @Test
    public void testCelsiusToKelvin() {
        assertEquals(307.35, c.convert("C", "K", 34.2),  0.000001);
    }

    @Test
    public void testKelvinToFahrenheit() {
        assertEquals(174.866, c.convert("K", "F", 352.52),  0.000001);
    }
}
