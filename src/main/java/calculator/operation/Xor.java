package calculator.operation;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.variables.CalculatorVariable;
import calculator.variables.MyBoolean;

import java.util.List;

public class Xor extends Operation {
    public Xor(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "xor";
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
