package calculator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Minus extends Operation
{

  public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
	super(elist);
	symbol = "-";
	neutral = 0;
	}
  
  public int op(int l, int r) {
  	return (l-r);
  }

	@Override
	public Duration op(ZonedDateTime l, ZonedDateTime r) {
		return Duration.between(l,r);
	}
}
