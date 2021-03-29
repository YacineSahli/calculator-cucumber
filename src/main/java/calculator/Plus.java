package calculator;

import java.util.List;

final public class Plus extends Operation
{

  public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
	super(elist);
	symbol = "+";
	neutral = 0;
  }
/*
  public int op(int l, int r) {
  	return (l+r);
  }
  */
	public CalculatorValue op(IntegerNumber l, IntegerNumber r){
		return new IntegerNumber(l.getValue()+r.getValue());
	}
}
