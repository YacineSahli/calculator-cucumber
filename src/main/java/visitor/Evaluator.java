package visitor;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.Function;
import calculator.Operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class Evaluator extends Visitor {

    private CalculatorValue computedValue;

    public CalculatorValue getResult() {
        return computedValue;
    }

    public void visit(CalculatorValue v) {
        computedValue = v;
    }

    @Override
    public void visit(Function f) throws EvaluatorException {
        CalculatorValue evaluatedArg;
        f.arg.accept(this);
        evaluatedArg = computedValue;
        try {
            computedValue = f.apply(evaluatedArg);
        } catch (InvocationTargetException e) {
            throw new EvaluatorException("Impossible to evaluate the expression: " + e.getCause().getMessage(), f);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void visit(Operation o) throws EvaluatorException {
        ArrayList<CalculatorValue> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for (Expression a : o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        CalculatorValue temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for (int counter = 1; counter < max; counter++) {
            try {
                temp = o.op(temp, evaluatedArgs.get(counter));
            } catch (InvocationTargetException e) {
                throw new EvaluatorException("Impossible to evaluate the expression: " + e.getCause().getMessage(), o);
            } catch (NoSuchMethodException e) {
                throw new EvaluatorException("Operation " + o.toString() + " don't exist for: " + temp.getClass().getName()
                        + " and " + evaluatedArgs.get(counter).getClass().getName(), o);
            }
        }
        // store the accumulated result
        computedValue = temp;
    }
}
