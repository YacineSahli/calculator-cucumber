package calculator.Boolean;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Operation;

import java.util.List;

public class Implication extends Operation {

    public Implication(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = ">";
    }
    public CalculatorValue op(MyBoolean l, MyBoolean r) {
        int lval = l.getValue();
        int rval = r.getValue();
        if (lval == 0 && rval == 1){
            return new MyBoolean(1);
        }
        else {
            return new MyBoolean(0);
        }
    }

}
