package cucumbertests.calculator;

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.operation.*;
import calculator.variables.IntegerNumber;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import visitor.EvaluatorException;
import visitor.Printer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class CalculatorSteps {

    private ArrayList<Expression> params;
    private Operation op;
    private Calculator c;

    @Before
    public void resetMemoryBeforeEachScenario() {
        params = null;
        op = null;
    }

    @Given("I initialise a calculator")
    public void givenIInitialiseACalculator() {
        c = new Calculator();
    }

    @Given("an integer operation {string}")
    public void givenAnIntegerOperation(String s) {
        // Write code here that turns the phrase above into concrete actions
        params = new ArrayList<>(); // create an empty set of parameters to be filled in
        try {
            switch (s) {
                case "+": {
                    op = new Plus(params);
                    break;
                }
                case "-": {
                    op = new Minus(params);
                    break;
                }
                case "*": {
                    op = new Times(params);
                    break;
                }
                case "/": {
                    op = new Divides(params);
                    break;
                }
                case "^": {
                    op = new Pow(params);
                    break;
                }
                default: {
                    fail();
                }
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    // The following example shows how to use a DataTable provided as input.
    // (The example looks slightly complex, since DataTables can take as input
    //  tables in two dimensions, i.e. rows and lines. This is why the input
    //  is a list of lists.
    @Given("the following list of integer numbers")
    public void givenTheFollowingListOfIntegerNumbers(List<List<String>> numbers) {
        params = new ArrayList<>();
        // Since we only use one line of input, we use get(0) to take the first line of the list,
        // which is a list of strings, that we will manually convert to integers:
        numbers.get(0).forEach(n -> params.add(new IntegerNumber(Integer.parseInt(n))));
        params.forEach(n -> System.out.println("value =" + n));
        op = null;
    }

    // The string in the Given annotation shows how to use regular expressions...
    // In this example, the notation d+ is used to represent numbers, i.e. nonempty sequences of digits
    @Given("^the sum of two numbers (\\d+) and (\\d+)$")
    // The alternative, and in this case simpler, notation would be:
    // @Given("the sum of two numbers {int} and {int}")
    public void givenTheSum(int n1, int n2) {
        try {
            params = new ArrayList<>();
            params.add(new IntegerNumber(n1));
            params.add(new IntegerNumber(n2));
            op = new Plus(params);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Then("^its (.*) notation is (.*)$")
    public void thenItsNotationIs(String notation, String s) {
        if (notation.equals("PREFIX") || notation.equals("POSTFIX") || notation.equals("INFIX")) {
            //op.notation = Notation.valueOf(notation);
            Printer p = new Printer(Notation.valueOf(notation), ",");
            try {
                op.accept(p);
            } catch (EvaluatorException e) {
                e.printStackTrace();
            }
            assertEquals(s, p.getResult());
        } else fail(notation + " is not a correct notation! ");
    }

    @When("^I provide a (.*) number (\\d+)$")
    public void whenIProvideANumber(String s, int val) {
        params.add(new IntegerNumber(val));
    }

    @Then("^the (.*) is (\\d+)$")
    public void thenTheOperationIs(String s, Integer val) {
        try {
            switch (s) {
                case "sum": {
                    op = new Plus(params);
                    break;
                }
                case "product": {
                    op = new Times(params);
                    break;
                }
                case "quotient": {
                    op = new Divides(params);
                    break;
                }
                case "difference": {
                    op = new Minus(params);
                    break;
                }
                case "pow": {
                    op = new Pow(params);
                    break;
                }
                default:
                    fail();
            }
            assertEquals(val.intValue(), ((IntegerNumber) c.eval(op)).getValue());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Then("the operation evaluates to {int}")
    public void thenTheOperationEvaluatesTo(Integer val) {
        //During previous @When steps, extra parameters may have been added to the operation
        //so we complete its parameter list here:
        op.addMoreParams(params);
        assertEquals(val.intValue(), ((IntegerNumber) c.eval(op)).getValue());

    }

}
