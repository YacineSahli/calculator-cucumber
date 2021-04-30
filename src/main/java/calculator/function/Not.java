package calculator.function;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.variables.CalculatorVariable;
import calculator.variables.MyBoolean;

public class Not extends Function {


    public Not(Expression exp) throws IllegalConstruction {
        super(exp);
        symbol = "!";
    }


    public CalculatorVariable apply(MyBoolean b) {
        if (b.getValue() == 0)
            return new MyBoolean(1);
        else
            return new MyBoolean(0);
    }
}
