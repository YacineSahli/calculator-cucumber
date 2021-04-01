package calculator.Boolean;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Operation;

import java.util.List;

public class Equivalence extends Operation {

    public Equivalence(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }
    public CalculatorValue op(MyBoolean l, MyBoolean r) {
        int lval = l.getValue();
        int rval = r.getValue();
        if (lval == rval){
            return new MyBoolean(0);
        }
        else {
            return new MyBoolean(1);
        }
    }
}