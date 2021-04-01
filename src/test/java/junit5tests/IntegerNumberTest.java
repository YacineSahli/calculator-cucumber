package junit5tests;

import calculator.IntegerNumber;
import calculator.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertNotEquals(number, new RationalNumber(value1, value2));
    }

    @Test
    void toIntegerNumber() {
        assertEquals(number, number.toIntegerNumber());
    }

    @Test
    void toRationalNumber() {
        assertEquals(new RationalNumber(value1, 1), number.toRationalNumber());
    }

    @Test
    public void testToString() {
        assertEquals("7", number.toString());
    }
}