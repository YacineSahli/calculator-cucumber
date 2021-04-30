package calculator.function;


import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.operation.Plus;
import calculator.variables.CalculatorVariable;
import calculator.variables.MyTime;

import java.time.ZonedDateTime;

public class PlusFunction extends Function {


    public PlusFunction(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol = "+";
    }


    public CalculatorVariable apply(MyTime t) {
        return Plus.op(new MyTime(ZonedDateTime.now()), t);
    }
}
