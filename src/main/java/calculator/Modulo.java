package calculator;

import java.util.List;

public class Modulo extends Operation{
    public Modulo(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "%";

        /*
        * neutral for modulon dont't exist so this value is not exact but better
        * than the default int value in Java
        * */
        neutral = Integer.MAX_VALUE;
    }

    public CalculatorValue op(IntegerNumber a, IntegerNumber n){
        if(n.getValue() == 0){
            throw new ArithmeticException("modulo by zero");
        }
        return new IntegerNumber(a.getValue() % n.getValue());
    }
}
