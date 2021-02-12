package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***************************************
 * A very simple Calculator in Java    *
 * Tom Mens, February 2021             *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

public class Main {

  public static void main(String[] args) {

  	Expression e;
  	Calculator c = new Calculator();


	try{
		// Here is an example of how to use the calculator:

		e = new MyNumber(8);
		c.print(e);
		c.eval(e);
		System.out.println("---");

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
	    e = new Plus(params);
		c.printExpressionDetails(e);
		c.eval(e);
		System.out.println("---");

	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
		e = new Minus(params2);
		c.print(e);
		c.eval(e);
		System.out.println("---");

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2));
		e = new Times(params3);
		c.printExpressionDetails(e);
		c.eval(e);
		System.out.println("---");

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(5));
		e = new Divides(params4);
		c.print(e);
		c.eval(e);
		System.out.println("---");

		List<Expression> params5 = new ArrayList<>();
		Collections.addAll(params5, new MyNumber(5), new MyNumber(0));
		e = new Divides(params5);
		c.print(e);
		c.eval(e);
	}

	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
