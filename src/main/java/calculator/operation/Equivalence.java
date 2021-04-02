package calculator.operation;

import calculator.variables.MyBoolean;
import calculator.variables.CalculatorVariable;
import calculator.Expression;
import calculator.IllegalConstruction;

import java.util.List;

public class Equivalence extends Operation {

    public Equivalence(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    public CalculatorVariable op(MyBoolean l, MyBoolean r) {
        int lval = l.getValue();
        int rval = r.getValue();
        if (lval == rval) {
            return new MyBoolean(0);
        } else {
            return new MyBoolean(1);
        }
    }
}
