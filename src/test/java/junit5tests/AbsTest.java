package junit5tests;

import java.util.ArrayList;
import java.util.Arrays;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import org.junit.jupiter.api.*;


public class AbsTest {

    private final int value1 = -8;
    private final int value2 = 6;
    private Abs func;
    private Expression param;
    private Calculator c = new Calculator();

    @BeforeEach
    public void setUp() {
        param = new IntegerNumber(value1);
        try { func = new Abs(param); }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    public void testConstructor1() {
        assertThrows(IllegalConstruction.class, () -> func = new Abs(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        try {
            assertNotEquals(func, new Invert(new IntegerNumber(value2)));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEquals() {
        try {
            Abs e = new Abs(new IntegerNumber(value1));
            assertEquals(func, e);
            assertEquals(e, e);
            assertNotEquals(e, new Abs(new IntegerNumber(value2)));
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testEquals2() {
        assertDoesNotThrow(() -> func.equals(null));
    }

    @Test
    public void testHashCode() {
        try {
            Abs e = new Abs(param);
            assertEquals(e.hashCode(), func.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    public void testNullParamList() {
        param = null;
        assertThrows(IllegalConstruction.class, () -> func = new Abs(param));
    }

    @Test
    public void testCountDepth() {
        assertEquals(Integer.valueOf(1), func.countDepth());
    }

    @Test
    public void testCountOps() {
        assertEquals(Integer.valueOf(1), func.countOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(1), func.countNbs());
    }

}
