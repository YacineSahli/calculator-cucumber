package calculator.operation;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;

import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "/";
        neutral = 1;
    }

    public CalculatorVariable op(IntegerNumber l, IntegerNumber r) throws ArithmeticException {
        if (r.getValue() == 0) {
            throw new ArithmeticException("Division per zero");
        }
        return new IntegerNumber(l.getValue() / r.getValue());
    }

    public CalculatorVariable op(RationalNumber l, RationalNumber r) {
        return buildNumber(l.getNum() * r.getDenum(), l.getDenum() * r.getNum());
    }
}
