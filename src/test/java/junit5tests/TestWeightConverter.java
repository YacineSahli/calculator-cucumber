package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWeightConverter {
    private final Calculator c = new Calculator();


    @Test
    public void testNewtonToPoundal() {
        assertEquals(7.23301, c.convert("N", "pdl", 1), 0.00001);
    }

    @Test
    public void testPoundalToDyne() {
        assertEquals(13825.5, c.convert("pdl", "dyne", 1), 0.00001);
    }
}
