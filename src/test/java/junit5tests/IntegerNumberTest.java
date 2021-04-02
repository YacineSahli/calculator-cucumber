package junit5tests;

import calculator.IntegerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerNumberTest {

    private final int value1 = 7;
    private final int value2 = 42;
    private IntegerNumber number;

    @BeforeEach
    void setUp() {
        number = new IntegerNumber(value1);
    }

    @Test
    void specificEquals() {
        assertEquals(number, new IntegerNumber(value1));
        assertNotEquals(number, new IntegerNumber(value2));
    }

    @Test
    void toIntegerNumber() {
        assertEquals(number, number.toIntegerNumber());
    }


    @Test
    public void testToString() {
        assertEquals("7", number.toString());
    }
}