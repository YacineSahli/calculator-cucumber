package calculator.Boolean;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.IntegerNumber;
import visitor.EvaluatorException;
import visitor.Visitor;

public class MyBoolean extends CalculatorValue {
    private final int value;
    private final static int ACCURACY = 3;

    public Integer getValue() { return value; }

    public MyBoolean(int v) throws IllegalArgumentException {
        super(Integer.toString(v), ACCURACY, true);
        if(v > 1 || v < 0){
            throw new IllegalArgumentException("Expected one or zero");
        }
        else {
            value=v;

        }
    }


    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof MyBoolean)) {
            return false;
        }
        MyBoolean oInt = (MyBoolean) o;
        return this.value == oInt.getValue();
    }
    public MyBoolean toMyBoolean(){
        return this;
    }
}
