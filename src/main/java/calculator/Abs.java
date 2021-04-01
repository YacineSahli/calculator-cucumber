package calculator;

public class Abs extends Function {

    public Abs(Expression arg) throws IllegalConstruction {
        super(arg);
    }

    public CalculatorValue apply(IntegerNumber i) {
        if (i.getValue() < 0)
            return new IntegerNumber(Math.abs(i.getValue()));
        return i;
    }

    public CalculatorValue apply(RationalNumber r) {
        return new RationalNumber(Math.abs(r.getNum()), Math.abs(r.getDenum()));
    }
}
