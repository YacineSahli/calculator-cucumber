package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestEvaluator {

    @SuppressWarnings("unused")
    private Evaluator visitor;
    private Calculator calc;
    private int value1, value2, zero;
    private String date1, date2, duration1, duration2, dateWithTimeZone1, dateWithTimeZone2, dateAMPM1, dateAMPM2;
    private Expression op;

    @BeforeEach
    public void setUp() {
        visitor = new Evaluator();
        calc = new Calculator();
        value1 = 8;
        value2 = 6;
        zero = 0;
        date1 = "2020-12-11 10:10:10";
        date2 = "2020-12-10 10:10:12";
        dateAMPM1 = "2020-12-10 10:10:10 AM";
        dateAMPM2 = "2020-12-10 10:10:10 PM";
        dateWithTimeZone1= "2020-12-10 10:10:10 PRC";
        dateWithTimeZone2= "2020-12-10 10:10:10 CET";
        duration1 = "PT3H40M";
        duration2 = "PT5H23M31S";
    }

    /* Unary MyTime operation tests
    @Test
    public void testEvaluatorDurationUnaryMinus() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(duration1));
            op = new Minus(list);
            assertEquals( "PT23H59M58S",
                    ((MyTime) calc.eval(op)).toString() );
        }
        catch(IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorDurationUnaryPlus() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(duration1));
            op = new Minus(list);
            assertEquals( "PT23H59M58S",
                    ((MyTime) calc.eval(op)).toString() );
        }
        catch(IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }
     */

    @Test
    public void testEvaluatorDateDifference() {
        try {
            List<Expression> list = new ArrayList<>();
            list.add(new MyTime(date1));
            list.add(new MyTime(date2));
            op = new Minus(list);
            assertEquals( "PT23H59M58S",
                    ((MyTime) calc.eval(op)).toString() );
        }
        catch(IllegalConstruction | ParseException e) {
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
        }
        catch(IllegalConstruction | ParseException e) {
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
        }
        catch(IllegalConstruction | ParseException e) {
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
        }
        catch(IllegalConstruction | ParseException e) {
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
        }
        catch(IllegalConstruction | ParseException e) {
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
            assertEquals( "PT-7H",
                    ((MyTime) calc.eval(op)).toString() );
        }
        catch(IllegalConstruction | ParseException e) {
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
            assertEquals( "PT-12H",
                    ((MyTime) calc.eval(op)).toString() );
        }
        catch(IllegalConstruction | ParseException e) {
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
            assertEquals( "PT1H",
                    ((MyTime) calc.eval(op)).toString() );
        }
        catch(IllegalConstruction | ParseException e) {
            e.printStackTrace();
            fail();
        }
    }



    @Test
    public void testEvaluatorMyNumber() {
        int result = ((IntegerNumber) calc.eval(new IntegerNumber(value1))).getValue();
        assertEquals( value1, result);
    }

    @Test
    public void testEvaluatorDividesPerZero() {
        /*
        try { op = new Divides(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(zero)));
            //assertThrows(ArithmeticException.class, () -> calc.eval(op));
            assertNull(calc.eval(op));
          }
        catch(IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
        //TODO BIG
         */
    }
    @Test
    public void testEvaluatorDivides() {
        try { op = new Divides(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber)calc.eval(op)).getValue();
            assertEquals( value1 / value2, result );

        }
        catch(IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorPlus() {
        try { op = new Plus(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber)calc.eval(op)).getValue();
            assertEquals( value1 + value2, result);
        }
        catch(IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorMinus() {
        try { op = new Minus(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber)calc.eval(op)).getValue();
            assertEquals( value1 - value2, result);
        }
        catch(IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvaluatorTimes() {
        try { op = new Times(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
            int result = ((IntegerNumber)calc.eval(op)).getValue();
            assertEquals( value1 * value2, result);
        }
        catch(IllegalConstruction e) {
            e.printStackTrace();
            fail();
        }
    }
}
