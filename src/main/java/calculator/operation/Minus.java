package calculator.operation;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;
import java.util.List;

final public class Minus extends Operation {

    public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "-";
        neutral = 0;
    }

    public CalculatorVariable op(IntegerNumber l, IntegerNumber r) {
        return new IntegerNumber(l.getValue() - r.getValue());
    }

    public CalculatorVariable op(RationalNumber l, RationalNumber r) {
        return NumberBuilder.builder().build((l.getNum() * r.getDenum()) - (l.getDenum() * r.getNum()),
                l.getDenum() * r.getDenum());
    }
}
