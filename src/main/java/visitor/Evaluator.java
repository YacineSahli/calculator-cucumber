package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;

import java.util.ArrayList;

public class Evaluator extends Visitor {

    private int computedValue;

    public Integer getResult() { return computedValue; }

    public void visit(MyNumber n) {
        computedValue = n.getValue();
    }

    public void visit(Operation o) throws VisitorException {
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        int temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            try {
                temp = o.op(temp, evaluatedArgs.get(counter));
            }catch (ArithmeticException e){
                // If a problem is encountered during the evaluation, throw a VisitorException
                throw new VisitorException("Impossible to evaluate Operation: "+e.getMessage(), o);
            }
        }
        // store the accumulated result
        computedValue = temp;
    }

}
