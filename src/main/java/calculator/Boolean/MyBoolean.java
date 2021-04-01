package calculator.Boolean;

import calculator.CalculatorValue;

public class MyBoolean extends CalculatorValue {
    private final static int ACCURACY = 3;
    private final int value;

    public MyBoolean(int v) throws IllegalArgumentException {
        super(Integer.toString(v), ACCURACY, true);
        if (v > 1 || v < 0) {
            throw new IllegalArgumentException("Expected one or zero");
        } else {
            value = v;

        }
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof MyBoolean)) {
            return false;
        }
        MyBoolean oInt = (MyBoolean) o;
        return this.value == oInt.getValue();
    }

    public MyBoolean toMyBoolean() {
        return this;
    }
}
