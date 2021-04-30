package calculator.operation;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;

import java.util.List;

/**
 * Operation that can compute the modulo.
 *
 * @author Arnaud.P
 */
public class Modulo extends Operation {
    public Modulo(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "%";
    }

    public CalculatorVariable op(IntegerNumber a, IntegerNumber n) {
        if (n.getValue() == 0) {
            throw new ArithmeticException("modulo by zero");
        }
        return new IntegerNumber(a.getValue() % n.getValue());
    }
}
