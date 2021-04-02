package junit5tests;

import calculator.IntegerNumber;
import calculator.RationalNumber;
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
    void specificEquals() {
        assertEquals(number, new RationalNumber(value1, value2));
        assertNotEquals(number, new RationalNumber(value2, value1));
        assertNotEquals(number, new IntegerNumber(value1));
    }

    @Test
    void getNum() {
        assertEquals(value1, number.getNum());
    }

    @Test
    void getDenum() {
        assertEquals(value2, number.getDenum());
    }

    @Test
    void constructor() {
        assertThrows(ArithmeticException.class, () -> new RationalNumber(value1, 0));
        assertDoesNotThrow(() -> new RationalNumber(value1, value2));
    }

    @Test
    void toRationalNumber() {
        assertEquals(number, number.toRationalNumber());
    }
}