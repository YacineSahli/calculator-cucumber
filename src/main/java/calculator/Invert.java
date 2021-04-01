package calculator;

public class Invert extends Function{

    public Invert(Expression arg) throws IllegalConstruction {
        super(arg);
        symbol = "<>";
    }

    public CalculatorValue apply(IntegerNumber i){
        return NumberBuilder.builder().build(1, i.getValue());
    }

    public CalculatorValue apply(RationalNumber r){
        return NumberBuilder.builder().build(r.getDenum(), r.getNum());
    }
}
