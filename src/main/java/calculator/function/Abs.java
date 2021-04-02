package calculator.function;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;

public class Abs extends Function {

    public Abs(Expression arg) throws IllegalConstruction {
        super(arg);
    }

    public CalculatorVariable apply(IntegerNumber i) {
        if (i.getValue() < 0)
            return new IntegerNumber(Math.abs(i.getValue()));
        return i;
    }
}
