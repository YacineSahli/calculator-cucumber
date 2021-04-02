package calculator.operation;

import calculator.variables.MyBoolean;
import calculator.variables.CalculatorVariable;
import calculator.Expression;
import calculator.IllegalConstruction;

import java.util.List;

public class Or extends Operation {

    public /*constructor*/ Or(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }


    public CalculatorVariable op(MyBoolean l, MyBoolean r) {
        return new MyBoolean(1 - (l.getValue() | r.getValue()));
    }
}
