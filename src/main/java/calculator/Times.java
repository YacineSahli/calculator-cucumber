package calculator;

import java.util.List;

final public class Times extends Operation
{

  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
	super(elist);
	symbol = "*";
	neutral = 1;
	}

	public CalculatorValue op(IntegerNumber l, IntegerNumber r){
		return new IntegerNumber(l.getValue()*r.getValue());
	}

	public CalculatorValue op(RationalNumber l, RationalNumber r){
  		return new RationalNumber(l.getNum()*r.getNum(), l.getDenum()*r.getDenum());
	}
}
