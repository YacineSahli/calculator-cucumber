package junit5tests.cli;

import cli.CommandLineInterface;
import cli.Mode;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCommandLineInterface {

    private CommandLineInterface cli;

    @Test
    public void testMode(){
        cli=new CommandLineInterface(Mode.CALCULATOR);
        assertNull(cli.parse("ConVertor"));
        assertEquals(Mode.CONVERTOR, cli.getMode());
        assertNull(cli.parse("calCulaTor"));
        assertEquals(Mode.CALCULATOR, cli.getMode());
    }
}
