package calculator;

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
    public CalculatorValue apply(IntegerNumber i) {
        return NumberBuilder.builder().build(1, i.getValue());
    }

    public CalculatorValue apply(RationalNumber r) {
        return NumberBuilder.builder().build(r.getDenum(), r.getNum());
    }
}
