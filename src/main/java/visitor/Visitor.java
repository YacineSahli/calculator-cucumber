package visitor;

import calculator.variables.CalculatorVariable;
import calculator.operation.Operation;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(CalculatorVariable n);
    public abstract void visit(Operation o) throws EvaluatorException;
}
