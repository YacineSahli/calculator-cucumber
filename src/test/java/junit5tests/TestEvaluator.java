package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import calculator.function.Abs;
import calculator.operation.*;
import calculator.variables.IntegerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class TestEvaluator {

    @SuppressWarnings("unused")
    private Evaluator visitor;
    private Calculator calc;
    private int value1, value2, value3, zero;
    private Expression op;
    private IntegerNumber i1, i2, minusI2;

    @BeforeEach
    public void setUp() {
        visitor = new Evaluator();
        calc = new Calculator();
        value1 = 8;
        value2 = 6;
        value3 = 3;
        zero = 0;
        i1 = new IntegerNumber(value1);
        i2 = new IntegerNumber(value2);
        minusI2 = new IntegerNumber(-value2);
    }

    @Test
    public void testEvaluatorMyNumber() {
        int result = ((IntegerNumber) calc.eval(new IntegerNumber(value1))).getValue();
        assertEquals(value1, result);
    }

    @Test
    public void testEvaluatorCalculatorValue() {
        int resultInt = ((IntegerNumber) calc.eval(new IntegerNumber(value1))).getValue();
        assertEquals(value1, resultInt);
    }

    @Test
    public void testEvaluatorDividesPerZero() {
        try {
            op = new Divides(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(zero)));
            assertNull(calc.eval(op));
        } catch (IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void testEvaluatorDivides() {
        try {
            // int int
            op = new Divides(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber) calc.eval(op)).getValue();
            assertEquals(value1 / value2, result);

        } catch (IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorPlus() {
        try {
            // int int
            op = new Plus(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber) calc.eval(op)).getValue();
            assertEquals(value1 + value2, result);

        } catch (IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorMinus() {
        try {
            // int int
            op = new Minus(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber) calc.eval(op)).getValue();
            assertEquals(value1 - value2, result);

        } catch (IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorTimes() {
        try {
            //int int
            op = new Times(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber) calc.eval(op)).getValue();
            assertEquals(value1 * value2, result);

        } catch (IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAbs() {
        try {
            op = new Abs(minusI2);
            assertEquals(i2, calc.eval(op));
            op = new Abs(i2);
            assertEquals(i2, calc.eval(op));

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
            fail();
        }
    }

    @Test
    public void testModularInverse() {
        try {
            op = new ModularInverse(Arrays.asList(i1, i2));
            assertNull(calc.eval(op));
            op = new ModularInverse(Arrays.asList(new IntegerNumber(3), new IntegerNumber(11)));
            assertEquals(new IntegerNumber(4), calc.eval(op));

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
            fail();
        }
    }

    @Test
    public void testModulo() {
        try {
            op = new Modulo(Arrays.asList(i1, i2));
            assertEquals(new IntegerNumber(i1.getValue() % i2.getValue()), calc.eval(op));

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
            fail();
        }
    }

    @Test
    public void testPow() {
        try {
            op = new Pow(Arrays.asList(i1, i2));
            assertEquals(new IntegerNumber((int) Math.pow(i1.getValue(), i2.getValue())), calc.eval(op));

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
            fail();
        }
    }

}
