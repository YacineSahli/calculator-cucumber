package junit5tests.cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.function.Abs;
import calculator.function.Invert;
import calculator.operation.ModularInverse;
import calculator.operation.Plus;
import calculator.operation.Pow;
import calculator.operation.Times;
import calculator.variables.IntegerNumber;
import cli.ArithmeticRunner;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArithmeticRunner {

    private ArithmeticRunner runner;
    private Expression plus, times, modinv, pow, abs, inv;
    int n1, n2;
    String n1_s, n2_s;

    @Before
    public void setUp() {
        n1=3;
        n2=11;

        runner = new ArithmeticRunner();
        n1_s = Integer.toString(n1);
        n2_s = Integer.toString(n2);
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, new IntegerNumber(n1), new IntegerNumber(n2));

        try {
            plus = new Plus(params);
            times = new Times(params);
            modinv = new ModularInverse(params);
            pow = new Pow(params);
            abs=new Abs(new IntegerNumber(-n1));
            inv = new Invert(new IntegerNumber(n1));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testPrefix(){
        //todo test incorrect expression
        assertEquals(plus, runner.parse("+("+n1_s+", "+n2_s+")"));
        assertEquals(plus, runner.parse(" + ( "+n1_s+"   "+n2_s+"   )"));

        assertEquals(times, runner.parse("*( "+n1_s+" , "+n2_s+") "));
        assertEquals(times, runner.parse(" * ("+n1_s+", "+n2_s+")   "));

        assertEquals(modinv, runner.parse("modinv ( "+n1_s+"  @  "+n2_s+" ) "));
        assertEquals(modinv, runner.parse("modinv ("+n1_s+", "+n2_s+")"));

        assertEquals(pow, runner.parse("^("+n1_s+" "+n2_s+")"));
        assertEquals(pow, runner.parse("^ ( "+n1_s+"@ "+n2_s+" ) "));
        assertEquals(pow, runner.parse(" pow ("+n1_s+", "+n2_s+")   "));
    }

    @Test
    public void testInfix(){
        //todo test incorrect expression
        assertEquals(plus, runner.parse(""+n1_s+"+"+n2_s+""));
        assertEquals(plus, runner.parse(" "+n1_s+"  +  "+n2_s+"   "));

        assertEquals(times, runner.parse(""+n1_s+"*"+n2_s+""));
        assertEquals(times, runner.parse(" "+n1_s+"  *  "+n2_s+"   "));

        assertEquals(modinv, runner.parse(""+n1_s+"modinv"+n2_s+""));
        assertEquals(modinv, runner.parse(" "+n1_s+"  modinv  "+n2_s+"   "));

        assertEquals(pow, runner.parse(""+n1_s+"^"+n2_s+""));
        assertEquals(pow, runner.parse(""+n1_s+"^ "+n2_s+""));
        assertEquals(pow, runner.parse(" "+n1_s+"  pow  "+n2_s+"   "));

        assertEquals(abs, runner.parse("abs( -"+n1_s+")"));
        assertEquals(abs, runner.parse("abs(-"+n1_s+")"));

        assertEquals(inv, runner.parse("inv( "+n1_s+")"));
        assertEquals(inv, runner.parse("inv("+n1_s+")"));
    }

    @Test
    public void testPostfix(){
        //todo test incorrect expression
        assertEquals(plus, runner.parse(" ("+n1_s+", "+n2_s+")  +  "));
        assertEquals(plus, runner.parse("  ( "+n1_s+"   "+n2_s+"   )+"));

        assertEquals(times, runner.parse("( "+n1_s+" , "+n2_s+") *"));
        assertEquals(times, runner.parse("  ("+n1_s+", "+n2_s+")*   "));

        assertEquals(modinv, runner.parse(" ( "+n1_s+"  @  "+n2_s+" ) modinv "));
        assertEquals(modinv, runner.parse(" ("+n1_s+", "+n2_s+") modinv"));

        assertEquals(pow, runner.parse("   ("+n1_s+" "+n2_s+")  ^ "));
        assertEquals(pow, runner.parse(" ( "+n1_s+"@ "+n2_s+" )^ "));
        assertEquals(pow, runner.parse("  ("+n1_s+", "+n2_s+") pow  "));
    }

}
