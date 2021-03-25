package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.MyTime;
import calculator.Operation;
import visitor.Evaluator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class TimeEvaluator extends TimeVisitor {
    private ZonedDateTime computedValue;

    public ZonedDateTime getResult() { return computedValue; }

    public void visit(MyTime n) {
        computedValue = n.getValue();
    }


    @Override
    public void visit(MyNumber n) { }

    public void visit(Operation o) throws EvaluatorException {
        ArrayList<ZonedDateTime> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        ZonedDateTime temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            try {
                temp = o.op(temp, evaluatedArgs.get(counter));
            }catch (ArithmeticException e){
                // If a problem is encountered during the evaluation, throw a VisitorException
                throw new EvaluatorException("Impossible to evaluate Operation: "+e.getMessage(), o);
            }
        }
        // store the accumulated result
        computedValue = temp;
    }
}
