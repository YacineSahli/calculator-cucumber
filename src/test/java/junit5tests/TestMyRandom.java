package junit5tests;

import calculator.CalculatorValue;
import calculator.IntegerNumber;
import calculator.MyRandom;
import calculator.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMyRandom {

    private MyRandom generator;

    @BeforeEach
    void setUp() {
        generator = new MyRandom(42);
    }

    @Test
    void nextInt() {
        CalculatorValue val = generator.nextInt();
        assertNotNull(val);
        assertEquals(IntegerNumber.class, val.getClass());
    }

    @Test
    void nextRational() {
        assertDoesNotThrow(() -> generator.nextRational());
        CalculatorValue val = generator.nextRational();
        assertNotNull(val);
        assertEquals(RationalNumber.class, val.getClass());
    }
}