package junit5tests.operation;

//Import Junit5 libraries for unit testing:

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.operation.Plus;
import calculator.operation.Times;
import calculator.variables.IntegerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTimes {

    private final int value1 = 8;
    private final int value2 = 6;
    private final Calculator c = new Calculator();
    private Times op;
    private List<Expression> params;

    @BeforeEach
    public void setUp() {
        params = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
        try {
            op = new Times(params);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testConstructor1() {
        // It should not be possible to create ans expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Times(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        // A Plus expression should not be the same as a Times expression
        try {
            assertNotEquals(op, new Plus(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
        try {
            Times e = new Times(p);
            assertEquals(op, e);
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
            Times e = new Times(p);
            assertEquals(e.hashCode(), op.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Times(params));
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
