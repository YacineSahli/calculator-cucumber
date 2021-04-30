package calculator.variables;

/**
 * An Integer number in the calculator.
 *
 * @author Arnaud.P
 */
public class IntegerNumber extends CalculatorVariable {

    private final static int ACCURACY = 2;
    private final int value;


    public /*constructor*/ IntegerNumber(int value) {
        super(Integer.toString(value), ACCURACY, true);
        this.value = value;
    }

    /**
     * @return the value of this integer.
     */
    public int getValue() {
        return value;
    }

    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof IntegerNumber)) {
            return false;
        }
        IntegerNumber oInt = (IntegerNumber) o;
        return this.value == oInt.getValue();
    }

    public IntegerNumber toIntegerNumber() {
        return this;
    }

    /**
     * @return the number converted to a rational number.
     */
    public RationalNumber toRationalNumber() {
        return new RationalNumber(this.value, 1);
    }
}
