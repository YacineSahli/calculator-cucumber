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

    public CalculatorValue op(RationalNumber a, IntegerNumber b){
        return NumberBuilder.builder().build((int) pow(a.getNum(), b.getValue()), (int) pow(a.getDenum(), b.getValue()));
    }
}
