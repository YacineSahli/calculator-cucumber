package calculator;

import visitor.EvaluatorException;
import visitor.Visitor;

import java.lang.reflect.InvocationTargetException;


public abstract class Function extends ComputableExpression implements Expression{
    public Expression arg;

    public Function(Expression arg) throws IllegalConstruction {
        if (arg == null) {
            throw new IllegalConstruction(); }
        else {
            this.arg = arg;
            funcName = "apply";
        }
    }

    public Expression getArg() {
        return arg;
    }

    public CalculatorValue apply(CalculatorValue ... args) throws InvocationTargetException{
        return op(args);
    }

    @Override
    public String toString() {
        return symbol;
    }


    @Override
    public void accept(Visitor v) throws EvaluatorException {
        arg.accept(v);
        v.visit(this);
    }

    @Override
    public Integer countDepth() {
        return 1 + arg.countDepth();
    }

    @Override
    public Integer countOps() {
        return 1+arg.countOps();
    }

    @Override
    public Integer countNbs() {
        return arg.countNbs();
    }
}
