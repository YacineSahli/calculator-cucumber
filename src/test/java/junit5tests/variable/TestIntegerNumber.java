package junit5tests.variable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestIntegerNumber {

    private final int value1 = 7;
    private final int value2 = 42;
    private IntegerNumber number;

    @BeforeEach
    void setUp() {
        number = new IntegerNumber(value1);
    }

    @Test
    void testSpecificEquals() {
        assertEquals(number, new IntegerNumber(value1));
        assertNotEquals(number, new IntegerNumber(value2));
        assertNotEquals(number, new RationalNumber(value1, value2));
    }

    @Test
    void testToIntegerNumber() {
        assertEquals(number, number.toIntegerNumber());
    }

    @Test
    void testToRationalNumber() {
        assertEquals(new RationalNumber(value1, 1), number.toRationalNumber());
    }

    @Test
    public void testToString() {
        assertEquals("7", number.toString());
    }
}