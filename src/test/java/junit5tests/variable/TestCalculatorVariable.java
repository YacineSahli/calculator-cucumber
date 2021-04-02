package junit5tests.variable;

import calculator.variables.IntegerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestCalculatorVariable {

    private final int value1 = 8;
    private final int value2 = 3;
    private IntegerNumber integerNumber;


    @BeforeEach
    void setUp() {
        integerNumber = new IntegerNumber(value1);

    }

    @Test
    void testCountDepth() {
        //test whether a value has zero depth (i.e. no nested expressions)
        assertEquals(Integer.valueOf(0), integerNumber.countDepth());

    }

    @Test
    void testCountOps() {
        //test whether a value contains zero operations
        assertEquals(Integer.valueOf(0), integerNumber.countOps());

    }

    @Test
    void testCountNbs() {
        //test whether a value contains 1 number
        assertEquals(Integer.valueOf(1), integerNumber.countNbs());

    }

    @Test
    void testEquals() {
        assertNotEquals(integerNumber, null);
        assertEquals(integerNumber, integerNumber);
    }
}