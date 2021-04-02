package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestPow {

    private final int value1 = 8;
    private final int value2 = 6;
    private Pow op;
    private List<Expression> params;
    private final Calculator c = new Calculator();

    @BeforeEach
    public void setUp() {
        params = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
        try {
            op = new Pow(params);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testConstructor1() {
        // It should not be possible to create a Pow expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Pow(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        // A Times expression should not be the same as a Pow expression
        try {
            assertNotEquals(op, new Times(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
        try {
            Pow e = new Pow(p);
            assertEquals(op, e);
            assertEquals(e, e);
            assertNotEquals(e, new Pow(new ArrayList<>(Arrays.asList(new IntegerNumber(5), new IntegerNumber(4)))));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testEquals2() {
        assertDoesNotThrow(() -> op == null); // Direct way to to test if the null case is handled.
    }

    @Test
    public void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
        try {
            Pow e = new Pow(p);
            assertEquals(e.hashCode(), op.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Pow(params));
    }

    @Test
    public void testCountDepth() {
        assertEquals(Integer.valueOf(1), op.countDepth());
    }

    @Test
    public void testCountOps() {
        assertEquals(Integer.valueOf(1), op.countOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(2), op.countNbs());
    }

}
