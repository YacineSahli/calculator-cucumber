package junit5tests.cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.function.Abs;
import calculator.function.Invert;
import calculator.operation.*;
import calculator.variables.IntegerNumber;
import calculator.variables.MyTime;
import cli.ArithmeticRunner;

import cli.DateRunner;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDateRunner {

    private DateRunner runner;
    private Expression plus, minus;
    String date1, date2, duration1;

    @Before
    public void setUp() throws ParseException {
        runner = new DateRunner();
        date1 = "2020-12-10 10:10:10 GMT+03:00";
        date2 = "2020-12-10 10:10:10 CET";
        duration1 = "PT5H23M31S";

        List<Expression> params1 = new ArrayList<>();
        Collections.addAll(params1, new MyTime(date1), new MyTime(duration1));

        List<Expression> params2 = new ArrayList<>();
        Collections.addAll(params2, new MyTime(date1), new MyTime(date2));

        try {
            plus = new Plus(params1);
            minus = new Minus(params2);
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testPrefix(){
        assertEquals(plus, runner.parse("+("+date1+", "+duration1+")"));
        assertEquals(plus, runner.parse(" + ( "+date1+"   "+duration1+"   )"));

        assertEquals(minus, runner.parse("-("+date1+", "+date2+")"));
        assertEquals(minus, runner.parse(" - ( "+date1+"   "+date2+"   )"));
    }

    @Test
    public void testInfix(){
        assertEquals(plus, runner.parse(""+date1+"+"+duration1+""));
        assertEquals(plus, runner.parse(" "+date1+"  +  "+duration1+"   "));

        assertEquals(minus, runner.parse(""+date1+" - "+date2+""));
        assertEquals(minus, runner.parse(" "+date1+"  -  "+date2+"   "));
    }

    @Test
    public void testPostfix(){
        assertEquals(plus, runner.parse("("+date1+", "+duration1+")  +  "));
        assertEquals(plus, runner.parse("  ( "+date1+"   "+duration1+"   )+"));

        assertEquals(minus, runner.parse("("+date1+", "+date2+")  -  "));
        assertEquals(minus, runner.parse("  ( "+date1+"   "+date2+"   )-"));
    }
}