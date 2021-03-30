package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTimeConverter {
    private Calculator c = new Calculator();

    @Test
    public void testSecondToDays() {
        assertEquals(1, c.convert("s", "days", 86400),  0);
    }

    @Test
    public void testHourToMicroseconds() {
        assertEquals(3600000000.0, c.convert("h", "us", 1),  0.0001);
    }
}
