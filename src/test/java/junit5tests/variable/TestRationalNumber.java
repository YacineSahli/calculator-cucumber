package junit5tests.variable;

import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRationalNumber {

    private final int value1 = 7;
    private final int value2 = 42;
    private RationalNumber number;

    @BeforeEach
    void setUp() {
        number = new RationalNumber(value1, value2);
    }

    @Test
    void testSpecificEquals() {
        assertEquals(number, new RationalNumber(value1, value2));
        assertNotEquals(number, new RationalNumber(value2, value1));
        assertNotEquals(number, new IntegerNumber(value1));
    }

    @Test
    void testGetNum() {
        assertEquals(value1, number.getNum());
    }

    @Test
    void testGetDenum() {
        assertEquals(value2, number.getDenum());
    }

    @Test
    void testConstructor() {
        assertThrows(ArithmeticException.class, () -> new RationalNumber(value1, 0));
        assertDoesNotThrow(() -> new RationalNumber(value1, value2));
    }

    @Test
    void testToRationalNumber() {
        assertEquals(number, number.toRationalNumber());
    }
}