package calculator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Minus extends Operation {

    public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "-";
        neutral = 0;
    }

    public CalculatorValue op(IntegerNumber l, IntegerNumber r) {
        return new IntegerNumber(l.getValue() - r.getValue());
    }

}
