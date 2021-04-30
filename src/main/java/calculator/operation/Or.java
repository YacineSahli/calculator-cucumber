package calculator.operation;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.variables.CalculatorVariable;
import calculator.variables.MyBoolean;

import java.util.List;

public class Or extends Operation {

    public /*constructor*/ Or(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "or";
    }


    public CalculatorVariable op(MyBoolean l, MyBoolean r) {
        return new MyBoolean(l.getValue() | r.getValue());
    }
}
