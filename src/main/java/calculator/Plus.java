package calculator;

import java.util.List;

final public class Plus extends Operation {

    public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "+";
        neutral = 0;
    }

    public static CalculatorValue op(MyTime l, MyTime r) {
        if (l.getZonedDateTime() != null) {
            // Not meaningful to add two ZonedDateTime
            if (r.getZonedDateTime() != null) {
                return null;
            } else {
                return new MyTime(l.getZonedDateTime().plus(r.getLocalTime()));
            }
        }
        // Two temporal amount
        else {
            // Not meaningful to add ZonedDateTime to LocalTime
            if (r.getZonedDateTime() != null) {
                return null;
            } else {
                return new MyTime(l.getLocalTime().plus(r.getLocalTime()));
            }
        }
    }

    public CalculatorValue op(IntegerNumber l, IntegerNumber r) {
        return new IntegerNumber(l.getValue() + r.getValue());
    }

    public CalculatorValue op(RationalNumber l, RationalNumber r) {
        return NumberBuilder.builder().build((l.getNum() * r.getDenum()) + (l.getDenum() * r.getNum()),
                l.getDenum() * r.getDenum());
    }
}
