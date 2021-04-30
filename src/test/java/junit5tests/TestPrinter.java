package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.operation.Divides;
import calculator.operation.Minus;
import calculator.operation.Plus;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.EvaluatorException;
import visitor.Printer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPrinter {

    private Printer printer;
    private Expression e1, e2;

    @BeforeEach
    public void SetUp() {
        List<Expression> params = new ArrayList<>();//plus
        Collections.addAll(params, new IntegerNumber(3), new RationalNumber(4, 3), new IntegerNumber(5));
        List<Expression> params2 = new ArrayList<>();//minus
        Collections.addAll(params2, new RationalNumber(5, 7), new IntegerNumber(3));
        List<Expression> params3 = new ArrayList<>();//devide

        try {
            e1 = new Plus(params); // level 1
            Expression eComp = new Minus(params2);

            Collections.addAll(params3, e1, eComp, new IntegerNumber(2));
            e2 = new Divides(params3); // level 2

        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }

    }

    @Test
    public void testPrefix() {
        printer = new Printer(Notation.PREFIX, "#");
        String r;
        try {
            e1.accept(printer);
            r = printer.getResult();
            assertEquals("+ (3#4/3#5)", r);

            e2.accept(printer);
            r = printer.getResult();
            assertEquals("/ (+ (3#4/3#5)#- (5/7#3)#2)", r);

        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInfix() {
        printer = new Printer(Notation.INFIX, "#");
        String r;
        try {
            e1.accept(printer);
            r = printer.getResult();
            assertEquals("( 3 + 4/3 + 5 )", r);

            e2.accept(printer);
            r = printer.getResult();
            assertEquals("( ( 3 + 4/3 + 5 ) / ( 5/7 - 3 ) / 2 )", r);

        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostfix() {
        printer = new Printer(Notation.POSTFIX, "#");
        String r;
        try {
            e1.accept(printer);
            r = printer.getResult();
            assertEquals("(3#4/3#5) +", r);

            printer.setSeparator("@");

            e2.accept(printer);
            r = printer.getResult();
            assertEquals("((3@4/3@5) +@(5/7@3) -@2) /", r);

        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }
}
