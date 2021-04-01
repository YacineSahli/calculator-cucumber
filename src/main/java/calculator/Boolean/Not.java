package calculator.Boolean;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.Function;
import calculator.IllegalConstruction;

public class Not extends Function {


    public Not(Expression exp) throws IllegalConstruction {
        super(exp);
        symbol = "!";
    }


    public CalculatorValue apply(MyBoolean b) {
        if (b.getValue() == 0)
            return new MyBoolean(1);
        else
            return new MyBoolean(0);
    }
}
