package calculator;

import visitor.EvaluatorException;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO EXPERIMENTAL
 * TO complete
 */
public abstract class Function implements Expression{

    public Expression arg;

    public Function(Expression arg) throws IllegalConstruction {
        if (arg == null) {
            throw new IllegalConstruction(); }
        else {
            this.arg = arg;
        }
    }

    public Expression getArg() {
        return arg;
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
        return arg.countOps();
    }

    @Override
    public Integer countNbs() {
        return arg.countNbs();
    }
}
