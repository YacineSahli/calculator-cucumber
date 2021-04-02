package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestEvaluator {

    @SuppressWarnings("unused")
    private Evaluator visitor;
    private Calculator calc;
    private int value1, value2, value3, zero;
    private String date1, date2, duration1, duration2, dateWithTimeZone1, dateWithTimeZone2, dateAMPM1, dateAMPM2;
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
        r1 = new RationalNumber(value1, value2);
        r2 = new RationalNumber(value3, value1);
        minusR2 = new RationalNumber(-value3, value1);

    }

    // Unary MyTime operation tests
    @Test
    public void testEvaluatorDurationUnaryMinus() {
        try {
            MyTime time = new MyTime(duration1);
            op = new MinusFunction(time);
            assertEquals(ZonedDateTime.now().minus(time.getAsDuration()).toEpochSecond(),
                    ((MyTime) calc.eval(op)).getZonedDateTime().toEpochSecond(),0.01);
        }
        catch(IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDurationUnaryPlus() {
        try {
            MyTime time = new MyTime(duration1);
            op = new PlusFunction(time);
            assertEquals(ZonedDateTime.now().plus(time.getAsDuration()).toEpochSecond(),
                    ((MyTime) calc.eval(op)).getZonedDateTime().toEpochSecond(),0.01);
        }
        catch(IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testEvaluatorDateDifference() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(date1));
            list.add(new MyTime(date2));
            op = new Minus(list);
            assertEquals("PT23H59M58S",
                    calc.eval(op).toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDateMinusTime() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(date1));
            list.add(new MyTime(duration1));
            op = new Minus(list);
            assertEquals("2020-12-11T06:30:10Z",
                    ((MyTime) calc.eval(op)).getZonedDateTime().toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorTimeMinusTime() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(duration1));
            list.add(new MyTime(duration2));
            op = new Minus(list);
            assertEquals("PT-1H-43M-31S",
                    ((MyTime) calc.eval(op)).getLocalTime().toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDatePlusTime() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(date1));
            list.add(new MyTime(duration1));
            op = new Plus(list);
            assertEquals("2020-12-11T13:50:10Z",
                    ((MyTime) calc.eval(op)).getZonedDateTime().toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorTimePlusTime() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(duration2));
            list.add(new MyTime(duration1));
            op = new Plus(list);
            assertEquals("PT9H3M31S",
                    ((MyTime) calc.eval(op)).getLocalTime().toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDateWithZoneDifference() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(dateWithTimeZone1));
            list.add(new MyTime(dateWithTimeZone2));
            op = new Minus(list);
            assertEquals("PT-7H",
                    calc.eval(op).toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDateAMPMDifference() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(dateAMPM1));
            list.add(new MyTime(dateAMPM2));
            op = new Minus(list);
            assertEquals("PT-12H",
                    calc.eval(op).toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDateAMPMMixedWithDateZoneDifference() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(dateAMPM1));
            list.add(new MyTime(dateWithTimeZone2));
            op = new Minus(list);
            assertEquals("PT1H",
                    calc.eval(op).toString());
        } catch (IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
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

    @Test
    public void testAbs() {
        try {
            op = new Abs(minusI2);
            assertEquals(i2, calc.eval(op));
            op = new Abs(minusR2);
            assertEquals(r2, calc.eval(op));
            op = new Abs(r2);
            assertEquals(r2, calc.eval(op));
            op = new Abs(i2);
            assertEquals(i2, calc.eval(op));

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
            fail();
        }
    }

    @Test
    public void testInvert() {
        try {
            op = new Invert(i1);
            assertEquals(new RationalNumber(1, i1.getValue()), calc.eval(op));
            op = new Invert(r1);
            assertEquals(new RationalNumber(r1.getDenum(), r1.getNum()), calc.eval(op));
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
            op = new ModularInverse(Arrays.asList(i1, r2));
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
            op = new Modulo(Arrays.asList(i1, r2));
            assertNull(calc.eval(op));

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

            op = new Pow(Arrays.asList(r1, i2));
            assertEquals(new RationalNumber((int) Math.pow(r1.getNum(), i2.getValue()), (int) Math.pow(r1.getDenum(), i2.getValue())), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
            fail();
        }
    }

}
