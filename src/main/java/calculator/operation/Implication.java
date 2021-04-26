package calculator.operation;

import calculator.variables.MyBoolean;
import calculator.variables.CalculatorVariable;
import calculator.Expression;
import calculator.IllegalConstruction;

import java.util.List;

public class Implication extends Operation {

    public Implication(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "=>";
    }

    public CalculatorVariable op(MyBoolean l, MyBoolean r) {
        int lval = l.getValue();
        int rval = r.getValue();
        if (lval == 0 && rval == 1) {
            return new MyBoolean(0);
        } else {
            return new MyBoolean(1);
        }
    }

}
