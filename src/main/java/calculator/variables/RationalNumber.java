package calculator.variables;

/**
 * Rational number in the calculator.
 *
 * @author Arnaud.P
 */
public class RationalNumber extends CalculatorVariable {

    private final static int ACCURACY = 1;

    private final int num;
    private final int denum;


    public RationalNumber(int num, int denum) {
        super(num + "/" + denum, ACCURACY, true);
        if (denum == 0) {
            throw new ArithmeticException("Denominator is zero");
        }
        this.num = num;
        this.denum = denum;
    }

    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof RationalNumber)) {
            return false;
        }
        RationalNumber oRat = (RationalNumber) o;
        return this.num == oRat.getNum() && this.denum == oRat.getDenum();
    }

    public int getNum() {
        return num;
    }

    public int getDenum() {
        return denum;
    }

    public RationalNumber toRationalNumber() {
        return this;
    }
}
