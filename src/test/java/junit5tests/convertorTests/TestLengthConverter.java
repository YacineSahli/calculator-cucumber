package junit5tests.convertorTests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLengthConverter {


    private final Calculator c = new Calculator();


    @Test
    public void testMeterToInch() {
        assertEquals(39.37008, c.convert("m", "in", 1), 0.00001);
    }

    @Test
    public void inchToMeter() {
        assertEquals(0.0254, c.convert("in", "m", 1), 0);
    }

    @Test
    public void testMeterToKilometer() {
        assertEquals(0.001, c.convert("m", "km", 1), 0);
    }

    @Test
    public void testYardToFeet() {
        assertEquals(3, c.convert("yd", "ft", 1), 0.0001);
    }

    @Test
    public void testNauticalMilesToMeter() {
        assertEquals(1852, c.convert("nmi", "m", 1), 0);
    }

    @Test
    public void testMeterToNanometers() {
        assertEquals(1000000000, c.convert("m", "nm", 1), 0.000001);
    }

    @Test
    public void testMeterToDecimeters() {
        assertEquals(10, c.convert("m", "dm", 1), 0);
    }

    @Test
    public void testZeroMeter() {
        assertEquals(0, c.convert("m", "in", 0), 0);
    }


}
