package calculator;


import java.time.ZonedDateTime;

public class PlusFunction extends Function {


    public PlusFunction(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol = "+";
    }


    public CalculatorValue apply(MyTime t) {
        return Plus.op(new MyTime(ZonedDateTime.now()), t);
    }
}
