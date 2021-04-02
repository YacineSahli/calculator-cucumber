package calculator;


import java.time.ZonedDateTime;

public class MinusFunction extends Function {


    public MinusFunction(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol = "-";
    }


    public CalculatorValue apply(MyTime t) {
        return Minus.op(new MyTime(ZonedDateTime.now()), t);
    }
}
