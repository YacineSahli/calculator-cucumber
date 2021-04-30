package visitor;

import calculator.function.Function;
import calculator.operation.Operation;
import calculator.variables.CalculatorVariable;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(CalculatorVariable n);

    public abstract void visit(Function f) throws EvaluatorException;

    public abstract void visit(Operation o) throws EvaluatorException;
}
