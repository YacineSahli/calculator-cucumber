package visitor;

import calculator.MyNumber;
import calculator.MyTime;
import calculator.Operation;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(MyNumber n);
    public abstract void visit(Operation o) throws EvaluatorException;
    public abstract void visit(MyTime myTime);
}
