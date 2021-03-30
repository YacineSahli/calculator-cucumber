package calculator.Boolean;

import calculator.CalculatorValue;
import calculator.Expression;
import visitor.EvaluatorException;
import visitor.Visitor;

public class MyBooleanVariable extends CalculatorValue {
    private final Character symbol;
    private final static int ACCURACY = 3;
    public Character getCharacter() {
        return symbol;
    }

    public /*constructor*/ MyBooleanVariable(Character c) {
        super(c.toString(), 3, true);
        symbol = c;
    }
    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof MyBooleanVariable)) {
            return false;
        }
        MyBooleanVariable oInt = (MyBooleanVariable) o;
        return this.symbol == oInt.getCharacter();
    }
    public MyBooleanVariable toMyBooleanVariable(){
        return this;
    }

}
