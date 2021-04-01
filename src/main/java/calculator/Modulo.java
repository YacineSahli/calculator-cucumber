package calculator;

import java.util.List;

public class Modulo extends Operation {
    public Modulo(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "%";
    }

    public CalculatorValue op(IntegerNumber a, IntegerNumber n) {
        if (n.getValue() == 0) {
            throw new ArithmeticException("modulo by zero");
        }
        return new IntegerNumber(a.getValue() % n.getValue());
    }
}
