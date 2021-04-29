package calculator.function;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;

public class Abs extends Function {

    public Abs(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol="abs";
    }

    public CalculatorVariable apply(IntegerNumber i) {
        if (i.getValue() < 0)
            return new IntegerNumber(Math.abs(i.getValue()));
        return i;
    }

    public CalculatorVariable apply(RationalNumber r) {
        return new RationalNumber(Math.abs(r.getNum()), Math.abs(r.getDenum()));
    }
}
