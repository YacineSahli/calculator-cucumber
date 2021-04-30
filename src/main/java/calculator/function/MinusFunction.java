package calculator.function;


import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.operation.Minus;
import calculator.variables.CalculatorVariable;
import calculator.variables.MyTime;

import java.time.ZonedDateTime;

public class MinusFunction extends Function {


    public MinusFunction(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol = "-";
    }


    public CalculatorVariable apply(MyTime t) {
        return Minus.op(new MyTime(ZonedDateTime.now()), t);
    }
}
