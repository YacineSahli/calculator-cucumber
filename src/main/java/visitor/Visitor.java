package visitor;

import calculator.CalculatorValue;
import calculator.Function;
import calculator.Operation;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(CalculatorValue n);
    public abstract void visit(Function f) throws EvaluatorException;
    public abstract void visit(Operation o) throws EvaluatorException;
}
