package calculator;

public class RationalNumber extends CalculatorValue{

    private final static int ACCURACY = 1;
    private int num;
    private int denum;


    public RationalNumber(String strValue) {
        super(strValue, 1, true);
    }

    public RationalNumber(int num, int denum){
        super(num+"/"+denum, 1, true);
    }

    @Override
    public boolean specificEquals(Object o) {
        return false;
    }
}
