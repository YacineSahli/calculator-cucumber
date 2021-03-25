package visitor;

import calculator.MyTime;
import calculator.Operation;

public abstract class TimeVisitor extends Visitor {
    public abstract void visit(MyTime n);
    public abstract void visit(Operation o) throws EvaluatorException;
}
