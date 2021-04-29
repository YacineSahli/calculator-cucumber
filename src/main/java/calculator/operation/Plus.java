package calculator.operation;

import calculator.*;
import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.MyTime;
import calculator.variables.RationalNumber;

import java.util.List;

final public class Plus extends Operation {

    public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "+";
        neutral = 0;
    }

    public static CalculatorVariable op(MyTime l, MyTime r) {
        if (l.getZonedDateTime() != null) {
            // Not meaningful to add two ZonedDateTime
            if (r.getZonedDateTime() != null) {
                throw new IllegalStateException("Not possible to sum two ZonedDateTime");
            } else {
                return new MyTime(l.getZonedDateTime().plus(r.getLocalTime()));
            }
        }
        // Two temporal amount
        else {
            // Not meaningful to add ZonedDateTime to LocalTime
            if (r.getZonedDateTime() != null) {
                throw new IllegalStateException("Not possible to add ZonedDateTime to a LocalTime");
            } else {
                return new MyTime(l.getLocalTime().plus(r.getLocalTime()));
            }
        }
    }

    public CalculatorVariable op(IntegerNumber l, IntegerNumber r) {
        return new IntegerNumber(l.getValue() + r.getValue());
    }

    public CalculatorVariable op(RationalNumber l, RationalNumber r) {
        return buildNumber((l.getNum() * r.getDenum()) + (l.getDenum() * r.getNum()),
                l.getDenum() * r.getDenum());
    }
}
