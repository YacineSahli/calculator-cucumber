package calculator;

public class IntegerNumber extends CalculatorValue{

    private int value;

    public IntegerNumber(String strValue){
        super(strValue, 1, true);
    }

    public IntegerNumber(int value){
        super(Integer.toString(value), 1, true);
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

    public IntegerNumber toIntegerNumber(){
        return this;
    }
}
