package junit5tests.cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.operation.And;
import calculator.operation.Or;
import calculator.variables.MyBoolean;
import cli.BooleanRunner;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBooleanRunner {
    private BooleanRunner runner;
    private Expression And, Or;
    private int True, False;

    @Before
    public void setUp() throws ParseException {
        runner = new BooleanRunner();
        True = 1;
        False = 0;

        List<Expression> params1 = new ArrayList<>();
        Collections.addAll(params1, new MyBoolean(True), new MyBoolean(False));

        List<Expression> params2 = new ArrayList<>();
        Collections.addAll(params2, new MyBoolean(True), new MyBoolean(False));

        try {
            And = new And(params1);
            Or = new Or(params2);
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testPrefix(){
        assertEquals(And, runner.parse("and("+True+", "+False+")"));
        assertEquals(And, runner.parse(" and ( "+True+"   "+False+"   )"));
        assertEquals(Or, runner.parse("or("+True+", "+False+")"));
        assertEquals(Or, runner.parse(" or ( "+True+"   "+False+"   )"));
    }

    @Test
    public void testInfix(){
        assertEquals(And, runner.parse(""+True+"and"+False+""));
        assertEquals(And, runner.parse(" "+True+"  and  "+False+"   "));

        assertEquals(Or, runner.parse(""+True+" or "+False+""));
        assertEquals(Or, runner.parse(" "+True+"  or  "+False+"   "));
    }

    @Test
    public void testPostfix(){
        assertEquals(And, runner.parse("("+True+", "+False+")  and  "));
        assertEquals(And, runner.parse("  ( "+True+"   "+False+"   )and"));

        assertEquals(Or, runner.parse("("+True+", "+False+")  or  "));
        assertEquals(Or, runner.parse("  ( "+True+"   "+False+"   )or"));
    }
}
