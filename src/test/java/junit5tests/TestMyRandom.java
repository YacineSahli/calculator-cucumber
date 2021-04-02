package junit5tests;

import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.utils.MyRandom;
import calculator.variables.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMyRandom {

    private MyRandom generator;
    private int seed, bound;

    @BeforeEach
    void setUp() {
        seed = 42;
        bound = 13;
        generator = new MyRandom(seed);
    }

    @Test
    void testConstructor(){
        MyRandom r2 = new MyRandom();
        MyRandom r3 = new MyRandom(seed);
        assertNotNull(r2);
        assertNotNull(r3);
        assertEquals(r3, generator);
        assertNotEquals(r2, generator);
        assertNotEquals(r2, r3);
    }

    @Test
    void testNextInt() {
        CalculatorVariable val = generator.nextInt();
        assertNotNull(val);
        assertEquals(IntegerNumber.class, val.getClass());
    }

    @Test
    void testNextIntBound() {
        MyRandom generator = new MyRandom(seed);
        assertTrue(((IntegerNumber)generator.nextInt(new IntegerNumber(bound))).getValue()<bound);
    }

    @Test
    void testNextRational() {
        assertDoesNotThrow(() -> generator.nextRational());
        CalculatorVariable val = generator.nextRational();
        assertNotNull(val);
        assertEquals(RationalNumber.class, val.getClass());
    }
}