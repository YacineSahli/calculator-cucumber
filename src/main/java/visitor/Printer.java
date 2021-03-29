package visitor;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.Notation;
import calculator.Operation;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Printer extends Visitor {

    private String separator;
    private String result = "";
    private Notation notation;


    public Printer(Notation notation, String separator) {
        this.separator = separator;
        this.notation = notation;
    }

    @Override
    public void visit(CalculatorValue n) {
        result = n.toString();
    }

    @Override
    public void visit(Operation o) throws EvaluatorException {
        ArrayList<String> stringExpressions = new ArrayList<>();
        for (Expression expression : o.args) {
            expression.accept(this);
            stringExpressions.add(result);
        }
        Stream<String> s = stringExpressions.stream();

        switch (notation) {
            case INFIX:
                result = "( " +
                        s.reduce((s1, s2) -> s1 + " " + o.toString() + " " + s2).get() +
                        " )";
                break;
            case PREFIX:
                result = o.toString() + " " +
                        "(" +
                        s.reduce((s1, s2) -> s1 + separator + s2).get() +
                        ")";
                break;
            case POSTFIX:
                result = "(" +
                        s.reduce((s1, s2) -> s1 + separator + s2).get() +
                        ")" +
                        " " + o.toString();
                break;
            default:
                result = "This case should never occur.";
        }
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setNotation(Notation notation) {
        this.notation = notation;
    }

    public String getResult() {
        return this.result;
    }
}
