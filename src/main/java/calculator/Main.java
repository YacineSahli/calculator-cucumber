package calculator;

import java.text.ParseException;
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

		e = new IntegerNumber(8);
		c.print(e);
		c.eval(e);
		System.out.println("---");

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new IntegerNumber(3), new IntegerNumber(4), new IntegerNumber(5));
	    e = new Plus(params);
		c.printExpressionDetails(e);
		c.eval(e);
		System.out.println("---");

	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new IntegerNumber(5), new IntegerNumber(3));
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
		Collections.addAll(params4, new Plus(params), new Minus(params2), new IntegerNumber(5));
		e = new Divides(params4);
		c.print(e);
		c.eval(e);
		System.out.println("---");

		List<Expression> params5 = new ArrayList<>();
		Collections.addAll(params5, new IntegerNumber(5), new IntegerNumber(0));
		e = new Divides(params5);
		c.print(e);
		c.eval(e);
		System.out.println("---");


		List<Expression> params6 = new ArrayList<>();
		Collections.addAll(params6, new MyTime("2020-12-24 10:10:10"), new MyTime("2020-12-11 15:41:12"));
		e = new Minus(params6);
		c.print(e);
		MyTime res = (MyTime) c.eval(e);
		System.out.println("Formatted result: " + res.toHumanFormat("d"));
		System.out.println("Formatted result: " + res.toHumanFormat("hours"));
		System.out.println("Formatted result: " + res.toHumanFormat("Minutes"));
		System.out.println("Formatted result: " + res.toHumanFormat("SECONDS"));



	}

	catch(IllegalConstruction | ParseException exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
