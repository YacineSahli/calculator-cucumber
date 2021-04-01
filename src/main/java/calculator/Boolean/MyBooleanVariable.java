package calculator.Boolean;

import calculator.CalculatorValue;

public class MyBooleanVariable extends CalculatorValue {
    private final static int ACCURACY = 3;
    private final Character symbol;

    public /*constructor*/ MyBooleanVariable(Character c) {
        super(c.toString(), 3, true);
        symbol = c;
    }

    public Character getCharacter() {
        return symbol;
    }

    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof MyBooleanVariable)) {
            return false;
        }
        MyBooleanVariable oInt = (MyBooleanVariable) o;
        return this.symbol == oInt.getCharacter();
    }

    public MyBooleanVariable toMyBooleanVariable() {
        return this;
    }

}
