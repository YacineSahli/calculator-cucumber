package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCurrencyConverter {
    private Calculator c = new Calculator();

    @Test
    public void testEuroToUsd() {
        assertNotNull(c.convert("EUR", "USD", 100));
    }
}
