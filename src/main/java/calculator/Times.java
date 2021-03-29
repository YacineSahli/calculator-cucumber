package calculator;

import java.util.List;

final public class Times extends Operation
{

  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
	super(elist);
	symbol = "*";
	neutral = 1;
	}
  /*
  public int op(int l, int r)
    { return (l*r); }
   */

	public CalculatorValue op(IntegerNumber l, IntegerNumber r){
		return new IntegerNumber(l.getValue()*r.getValue());
	}
}
