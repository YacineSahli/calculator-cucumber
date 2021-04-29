package calculator.operation;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;

import java.util.List;

final public class Times extends Operation {

    public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "*";
        neutral = 1;
    }

    public CalculatorVariable op(IntegerNumber l, IntegerNumber r) {
        return new IntegerNumber(l.getValue() * r.getValue());
    }

    public CalculatorVariable op(RationalNumber l, RationalNumber r) {
        return new RationalNumber(l.getNum() * r.getNum(), l.getDenum() * r.getDenum());
    }
}
