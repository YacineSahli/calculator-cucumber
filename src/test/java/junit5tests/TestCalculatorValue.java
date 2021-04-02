package junit5tests;

import calculator.IntegerNumber;
import calculator.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestCalculatorValue {

    private final int value1 = 8;
    private final int value2 = 3;
    private IntegerNumber integerNumber;
    private RationalNumber rationalNumber;

    @BeforeEach
    void setUp() {
        integerNumber = new IntegerNumber(value1);
        rationalNumber = new RationalNumber(value1, value2);
    }

    @Test
    void testCountDepth() {
        //test whether a value has zero depth (i.e. no nested expressions)
        assertEquals(Integer.valueOf(0), integerNumber.countDepth());
        assertEquals(Integer.valueOf(0), rationalNumber.countDepth());
    }

    @Test
    void testCountOps() {
        //test whether a value contains zero operations
        assertEquals(Integer.valueOf(0), integerNumber.countOps());
        assertEquals(Integer.valueOf(0), rationalNumber.countOps());
    }

    @Test
    void testCountNbs() {
        //test whether a value contains 1 number
        assertEquals(Integer.valueOf(1), integerNumber.countNbs());
        assertEquals(Integer.valueOf(1), rationalNumber.countNbs());

    }

    @Test
    void testEquals() {
        assertNotEquals(integerNumber, null);
        assertNotEquals(rationalNumber, "Hello World !");
        assertEquals(integerNumber, integerNumber);
    }
}