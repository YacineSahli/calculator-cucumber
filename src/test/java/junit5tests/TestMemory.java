package junit5tests;

import calculator.*;
import calculator.operation.Divides;
import calculator.operation.Minus;
import calculator.operation.Plus;
import calculator.operation.Times;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMemory {
    private final Calculator calc = new Calculator();
    private Memory memory;
    private Expression expr1, expr2, expr3, expr4, expr5;
    private CalculatorVariable arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10;

    @BeforeEach
    public void setUp() {

        try {
            arg1 = new IntegerNumber(1);
            arg2 = new IntegerNumber(3);
            arg3 = new IntegerNumber(14);
            arg4 = new IntegerNumber(11);
            arg5 = new IntegerNumber(2);
            arg6 = new IntegerNumber(5);
            arg7 = new IntegerNumber(9);
            arg8 = new IntegerNumber(3);
            arg9 = new IntegerNumber(18);
            arg10 = new IntegerNumber(2);
            ArrayList<Expression> args1 = new ArrayList<>();
            ArrayList<Expression> args2 = new ArrayList<>();
            ArrayList<Expression> args3 = new ArrayList<>();
            ArrayList<Expression> args4 = new ArrayList<>();
            ArrayList<Expression> args5 = new ArrayList<>();
            args1.add(arg1);
            args1.add(arg2);
            args2.add(arg3);
            args2.add(arg4);
            args3.add(arg5);
            args3.add(arg6);
            args4.add(arg7);
            args4.add(arg8);
            args5.add(arg9);
            args5.add(arg10);
            expr1 = new Plus(args1);
            expr2 = new Minus(args2);
            expr3 = new Times(args3);
            expr4 = new Divides(args4);
            expr5 = new Plus(args5);

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }

    }

    @Test
    public void testAdd() {
        memory = new Memory(calc, 5);
        memory.add(expr1, null);
        memory.add(expr2, null);
        assertEquals(1, memory.getPointer());
        assertEquals(2, memory.getLoad());
    }

    @Test
    public void testUndo() {
        memory = new Memory(calc, 5);
        memory.add(expr1, null);
        memory.add(expr2, null);
        memory.add(expr3, null);
        Expression expression = memory.undo();
        assertEquals(expr2, expression);
        assertEquals(1, memory.getPointer());
        assertEquals(3, memory.getLoad());
    }

    @Test
    public void testSupplementaryUndo() {
        memory = new Memory(calc, 5);
        memory.add(expr1, null);
        memory.add(expr2, null);
        memory.add(expr3, null);
        Expression expression = memory.undo();
        expression = memory.undo();
        expression = memory.undo();
        assertEquals(expr1, expression);
        assertEquals(0, memory.getPointer());
        assertEquals(3, memory.getLoad());
    }

    @Test
    public void testRedo() {
        memory = new Memory(calc, 5);
        memory.add(expr1, null);
        memory.add(expr2, null);
        memory.add(expr3, null);
        Expression expression = memory.undo();
        expression = memory.undo();
        expression = memory.redo();
        assertEquals(expr2, expression);
        assertEquals(1, memory.getPointer());
        assertEquals(3, memory.getLoad());
    }

    @Test
    public void testSupplementaryRedo() {
        memory = new Memory(calc, 5);
        memory.add(expr1, null);
        memory.add(expr2, null);
        memory.add(expr3, null);
        Expression expression = memory.undo();
        expression = memory.redo();
        expression = memory.redo();
        assertEquals(expr3, expression);
        assertEquals(2, memory.getPointer());
        assertEquals(3, memory.getLoad());
    }

    @Test
    public void testAddAfterUndo() {
        memory = new Memory(calc, 5);
        memory.add(expr1, null);
        memory.add(expr2, null);
        memory.add(expr3, null);
        memory.undo();
        memory.undo();
        memory.add(expr4, null);
        assertEquals(expr4, memory.getExpressions()[1]);
        assertEquals(1, memory.getPointer());
        assertEquals(2, memory.getLoad());
        assertEquals(null, memory.getExpressions()[memory.getPointer() + 1]);
    }
    /*@Test
    public void testDisplayLog(){
        memory = new Memory(calc, 5);
        memory.add(expr1);
        memory.add(expr2);
        memory.add(expr3);
        memory.displayLog();
    }*/
    /*@Test
    public void testHistory(){
        memory = new Memory(calc, 5);
        memory.add(expr1);
        memory.add(expr2);
        memory.add(expr4);
        memory.history();
    }*/


}
