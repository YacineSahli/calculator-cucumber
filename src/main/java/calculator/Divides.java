package calculator;

import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "/";
        neutral = 1;
    }

    /*
        public int op(int l, int r) throws ArithmeticException {
            if (r == 0) {
                throw new ArithmeticException("Division per zero");
            } else {
                return (l / r);
            }
        }
     */
    public CalculatorValue op(IntegerNumber l, IntegerNumber r) throws ArithmeticException {
        if (r.getValue() == 0) {
            throw new ArithmeticException("Division per zero");
        }
        return new IntegerNumber(l.getValue() / r.getValue());
    }
}
