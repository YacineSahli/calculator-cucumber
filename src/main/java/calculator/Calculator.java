package calculator;


import visitor.Evaluator;
import visitor.EvaluatorException;
import visitor.Printer;

public class Calculator {
    Convertor convertor = new Convertor();

    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    public void print(Expression e) {
        // use Printer to display de expression with the INFIX notation
        Printer p = new Printer(Notation.INFIX, ",");
        try {
            e.accept(p);
            System.out.println("The result of evaluating expression " + p.getResult());
        } catch (EvaluatorException evaluatorException) {
            System.out.println(evaluatorException.getMessage());
        }
        System.out.println("is: " + eval(e) + ".");
        System.out.println();
    }

    public void printExpressionDetails(Expression e) {
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    public CalculatorValue eval(Expression e) {
        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        try {
            e.accept(v);
        } catch (EvaluatorException evaluatorException) {
            System.out.println(evaluatorException.getMessage());
            return null;
        }
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }


    public Double convert(String inputUnit, String outputUnit, double inputAmount) {
        return convertor.convert(inputUnit, outputUnit, inputAmount);
    }

    public Double convert(String input) {
        return convertor.convert(input);
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
