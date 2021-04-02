package calculator.operation;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;

import java.util.List;

import static java.lang.Math.pow;

public class Pow extends Operation {

    public Pow(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "^";
        neutral = 1;
    }

    public CalculatorVariable op(IntegerNumber a, IntegerNumber b) {
        return new IntegerNumber((int) pow(a.getValue(), b.getValue()));
    }
}
