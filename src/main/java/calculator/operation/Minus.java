package calculator.operation;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.MyTime;
import calculator.variables.RationalNumber;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Minus extends Operation {

    public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "-";
        neutral = 0;
    }

    public CalculatorVariable op(IntegerNumber l, IntegerNumber r) {
        return new IntegerNumber(l.getValue() - r.getValue());
    }

    public static CalculatorVariable op(MyTime l, MyTime r) {
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
                throw new IllegalStateException("Not possible to substract ZonedDateTime to a LocalTime");
            } else {
                return new MyTime(l.getLocalTime().minus(r.getLocalTime()));
            }
        }
    }

    public CalculatorVariable op(MyTime r) {
        MyTime l = new MyTime(ZonedDateTime.now());
        return op(l, r);
    }

    public CalculatorVariable op(RationalNumber l, RationalNumber r) {
        return buildNumber((l.getNum() * r.getDenum()) - (l.getDenum() * r.getNum()),
                l.getDenum() * r.getDenum());
    }
}
