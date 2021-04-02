package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
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
    private String date1, date2, duration1, duration2, dateWithTimeZone1, dateWithTimeZone2, dateAMPM1, dateAMPM2;
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
        date1 = "2020-12-11 10:10:10";
        date2 = "2020-12-10 10:10:12";
        dateAMPM1 = "2020-12-10 10:10:10 AM";
        dateAMPM2 = "2020-12-10 10:10:10 PM";
        dateWithTimeZone1 = "2020-12-10 10:10:10 PRC";
        dateWithTimeZone2 = "2020-12-10 10:10:10 CET";
        duration1 = "PT3H40M";
        duration2 = "PT5H23M31S";
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
}
