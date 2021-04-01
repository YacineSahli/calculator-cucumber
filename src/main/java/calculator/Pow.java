package calculator;

import java.util.List;
import static java.lang.Math.pow;

public class Pow extends Operation{

    public Pow(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "^";
        neutral = 1;
    }

    public CalculatorValue op(IntegerNumber a, IntegerNumber b){
        return new IntegerNumber((int) pow(a.getValue(), b.getValue()));
    }

    public CalculatorValue op(RationalNumber a, RationalNumber b){
        if(b.getDenum() != 1)
            throw new IllegalArgumentException("Can't use rational number as exponent");
        IntegerNumber bInt = new IntegerNumber(b.getNum());
        return NumberBuilder.builder().build((int) pow(a.getNum(), bInt.getValue()), (int) pow(a.getDenum(), bInt.getValue()));
    }
}
