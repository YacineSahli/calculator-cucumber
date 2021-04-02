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

    public static CalculatorValue op(MyTime l, MyTime r) {
        if (l.getZonedDateTime() != null) {
            if (r.getZonedDateTime() != null) {
                return new MyTime(Duration.between(r.getZonedDateTime(), l.getZonedDateTime()));
            } else {
                return new MyTime(l.getZonedDateTime().minus(r.getLocalTime()));
            }
        }
        // Two temporal amount
        else {
            // Not meaningful to subtract ZonedDateTime from LocalTime
            if (r.getZonedDateTime() != null) {
                return null;
            } else {
                return new MyTime(l.getLocalTime().minus(r.getLocalTime()));
            }
        }
    }

    public CalculatorValue op(MyTime r) {
        MyTime l = new MyTime(ZonedDateTime.now());
        return op(l, r);
    }
}
