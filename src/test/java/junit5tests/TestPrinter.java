package junit5tests;

import calculator.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import visitor.Printer;
import visitor.EvaluatorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestPrinter {

    private Printer printer;
    private Expression e1, e2;

    @BeforeEach
    public void SetUp(){
        List<Expression> params = new ArrayList<>();//plus
        Collections.addAll(params, new IntegerNumber(3), new IntegerNumber(4), new IntegerNumber(5));
        List<Expression> params2 = new ArrayList<>();//minus
        Collections.addAll(params2, new IntegerNumber(5), new IntegerNumber(3));
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
    public void testPrefix(){
        printer = new Printer(Notation.PREFIX, "#");
        String r;
        try {
            e1.accept(printer);
            r = printer.getResult();
            assertEquals("+ (3#4#5)",r);

            e2.accept(printer);
            r = printer.getResult();
            assertEquals("/ (+ (3#4#5)#- (5#3)#2)", r);

        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInfix(){
        printer = new Printer(Notation.INFIX, "#");
        String r;
        try {
            e1.accept(printer);
            r = printer.getResult();
            assertEquals("( 3 + 4 + 5 )",r);

            e2.accept(printer);
            r = printer.getResult();
            assertEquals("( ( 3 + 4 + 5 ) / ( 5 - 3 ) / 2 )", r);

        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostfix(){
        printer = new Printer(Notation.POSTFIX, "#");
        String r;
        try {
            e1.accept(printer);
            r = printer.getResult();
            assertEquals("(3#4#5) +",r);

            printer.setSeparator("@");

            e2.accept(printer);
            r = printer.getResult();
            assertEquals("((3@4@5) +@(5@3) -@2) /", r);

        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }
}
