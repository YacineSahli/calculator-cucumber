package calculator.Boolean;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Operation;

import java.util.List;

public class Or extends Operation {

    public /*constructor*/ Or(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }


    public CalculatorValue op(MyBoolean l, MyBoolean r) {
        return new MyBoolean(1 - (l.getValue() | r.getValue()));
    }
}
