package junit5tests.operation;

//Import Junit5 libraries for unit testing:

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.function.Not;
import calculator.operation.*;
import calculator.variables.MyBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanOperationTest {
    private Calculator calc;
    private MyBoolean first, second;
    private List<Expression> args;
    private Expression op;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();


    }

    @Test
    public void testAnd() {
        first = new MyBoolean(0);
        second = new MyBoolean(1);
        args = new ArrayList<>();
        args.add(first);
        args.add(second);
        try {
            op = new And(args);
            assertEquals(new MyBoolean(0), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testOr() {
        first = new MyBoolean(0);
        second = new MyBoolean(1);
        args = new ArrayList<>();
        args.add(first);
        args.add(second);
        try {
            op = new Or(args);
            assertEquals(new MyBoolean(1), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testXor() {
        first = new MyBoolean(0);
        second = new MyBoolean(1);
        args = new ArrayList<>();
        args.add(first);
        args.add(second);
        try {
            op = new Xor(args);
            assertEquals(new MyBoolean(1), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testImplication() {
        first = new MyBoolean(0);
        second = new MyBoolean(1);
        args = new ArrayList<>();
        args.add(first);
        args.add(second);
        try {
            op = new Implication(args);
            assertEquals(new MyBoolean(0), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testEquivalence() {
        first = new MyBoolean(0);
        second = new MyBoolean(1);
        args = new ArrayList<>();
        args.add(first);
        args.add(second);
        try {
            op = new Equivalence(args);
            assertEquals(new MyBoolean(0), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }

    @Test
    public void testNot() {
        first = new MyBoolean(0);

        try {
            op = new Not(first);
            assertEquals(new MyBoolean(1), calc.eval(op));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
    }


}
