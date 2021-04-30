package calculator.function;

import calculator.ComputableExpression;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.variables.CalculatorVariable;
import visitor.EvaluatorException;
import visitor.Visitor;

import java.lang.reflect.InvocationTargetException;


public abstract class Function extends ComputableExpression implements Expression {
    public Expression arg;

    public Function(Expression arg) throws IllegalConstruction {
        if (arg == null) {
            throw new IllegalConstruction();
        } else {
            this.arg = arg;
            funcName = "apply";
        }
    }

    public Expression getArg() {
        return arg;
    }

    public CalculatorVariable apply(CalculatorVariable... args) throws InvocationTargetException, NoSuchMethodException {
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
        return 1 + arg.countOps();
    }

    @Override
    public Integer countNbs() {
        return arg.countNbs();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Function function = (Function) o;

        return arg.equals(function.arg);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + arg.hashCode();
        return result;
    }
}
