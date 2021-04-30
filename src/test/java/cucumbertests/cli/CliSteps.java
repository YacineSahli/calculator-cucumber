package cucumbertests.cli;

import cli.CommandLineInterface;
import cli.Mode;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class CliSteps {

    private CommandLineInterface cli;
    private Mode currentMode;
    private String expression;

    @Before
    public void resetMemoryBeforeEachScenario() {
        currentMode = null;
        expression = null;
    }

    @Given("I initialise the cli")
    public void givenIInitialiseACalculator() {
        cli = new CommandLineInterface(Mode.CALCULATOR);
    }

    @Given("a mode name {string}")
    public void givenAModeName(String s) {
        Mode isMode = null;
        s = s.toUpperCase();
        for (Mode mode : Mode.values()) {
            if (s.equals(mode.toString()))
                isMode = mode;
        }
        assertNotNull(isMode);
        currentMode = isMode;
        assertNull(cli.parse(s));
    }

    @Then("the mode of the cli is {string}")
    public void thenTheModeIs(String s) {
        assertEquals(s.toUpperCase(), cli.getMode().toString().toUpperCase());
    }

    @Given("an expression {string}")
    public void givenAnExpression(String s) {
        expression = s;
    }

    @Then("the result of the evaluation is {string}")
    public void thenTheResultOfTheEvaluationIs(String s) {
        assertNotNull(currentMode);
        assertNotNull(expression);
        assertDoesNotThrow(() -> assertEquals(s, cli.parse(expression)));
    }

    @Then("the evaluation fail")
    public void theEvaluationFail() {
        assertNotNull(currentMode);
        assertNotNull(expression);
        assertDoesNotThrow(() -> assertTrue(cli.parse(expression).startsWith("ERROR ")));
    }


}
