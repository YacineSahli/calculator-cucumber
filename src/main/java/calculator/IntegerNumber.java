package calculator;

public class IntegerNumber extends CalculatorValue {

    private final static int ACCURACY = 2;
    private final int value;


    public IntegerNumber(int value) {
        super(Integer.toString(value), ACCURACY, true);
        this.value = value;
    }

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

    public RationalNumber toRationalNumber(){
        return new RationalNumber(this.value, 1);
    }
}
