package calculator.function;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;

/**
 * Function that can compute the inverse of a number.
 *
 * @author Arnaud.P
 */
public class Invert extends Function {

    public Invert(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol = "<>";
    }

    /**
     * @param i the integer to invert.
     * @return the integer inverted. i.e. a rational number equals to 1/i.
     */
    public CalculatorVariable apply(IntegerNumber i) {
        return buildNumber(1, i.getValue());
    }

    public CalculatorVariable apply(RationalNumber r) {
        return buildNumber(r.getDenum(), r.getNum());
    }
}
