package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;

import calculator.operation.*;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;
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
    private RationalNumber r1, r2, minusR2;

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
        r1 = new RationalNumber(value1, value2);
        r2 = new RationalNumber(value3, value1);
        minusR2 = new RationalNumber(-value3, value1);

    }

    @Test
    public void testEvaluatorMyNumber() {
        int result = ((IntegerNumber) calc.eval(new IntegerNumber(value1))).getValue();
        assertEquals(value1, result);
    }

    @Test
    public void testEvaluatorCalculatorValue() {
        int resultInt = ((IntegerNumber) calc.eval(new IntegerNumber(value1))).getValue();
        RationalNumber rationalNumber = ((RationalNumber) calc.eval(new RationalNumber(value1, value2)));
        assertEquals(value1, resultInt);
        assertEquals(value1, rationalNumber.getNum());
        assertEquals(value2, rationalNumber.getDenum());
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

            //rational rational
            op = new Divides(Arrays.asList(new RationalNumber(value1, value2), new RationalNumber(value3, value1)));
            RationalNumber result2 = ((RationalNumber) calc.eval(op));
            assertEquals(value1 * value1, result2.getNum());
            assertEquals(value2 * value3, result2.getDenum());

            //rational integer
            op = new Divides(Arrays.asList(new IntegerNumber(value2), new RationalNumber(value3, value1)));
            RationalNumber result3 = ((RationalNumber) calc.eval(op));
            assertEquals(value2 * value1, result3.getNum());
            assertEquals(value3, result3.getDenum());

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

            //rational rational
            op = new Plus(Arrays.asList(new RationalNumber(value1, value2), new RationalNumber(value1, value3)));
            RationalNumber result2 = ((RationalNumber) calc.eval(op));
            assertEquals((value1 * value3) + (value1 * value2), result2.getNum());
            assertEquals(value2 * value3, result2.getDenum());

            //int rational
            op = new Plus(Arrays.asList(new IntegerNumber(value1), new RationalNumber(value1, value3)));
            RationalNumber result3 = ((RationalNumber) calc.eval(op));
            assertEquals((value1 * value3) + (value1), result3.getNum());
            assertEquals(value3, result3.getDenum());
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

            //rational rational
            op = new Minus(Arrays.asList(new RationalNumber(value1, value2), new RationalNumber(value1, value3)));
            RationalNumber result2 = ((RationalNumber) calc.eval(op));
            assertEquals((value1 * value3) - (value1 * value2), result2.getNum());
            assertEquals(value2 * value3, result2.getDenum());

            //int rational
            op = new Minus(Arrays.asList(new IntegerNumber(value1), new RationalNumber(value1, value3)));
            RationalNumber result3 = ((RationalNumber) calc.eval(op));
            assertEquals((value1 * value3) - (value1), result3.getNum());
            assertEquals(value3, result3.getDenum());
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

            //rational rational
            op = new Times(Arrays.asList(new RationalNumber(value1, value2), new RationalNumber(value1, value3)));
            RationalNumber result2 = ((RationalNumber) calc.eval(op));
            assertEquals((value1 * value1), result2.getNum());
            assertEquals(value2 * value3, result2.getDenum());

            //int rational
            op = new Times((Arrays.asList(new IntegerNumber(value1), new RationalNumber(value1, value3))));
            RationalNumber result3 = ((RationalNumber) calc.eval(op));
            assertEquals((value1 * value1), result3.getNum());
            assertEquals(value3, result3.getDenum());
        } catch (IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

}
