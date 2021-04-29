package junit5tests.cli;

import cli.CommandLineInterface;
import cli.Mode;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCommandLineInterface {

    private CommandLineInterface cli;

    private class CliMock extends CommandLineInterface{

        public int eventHandling_call;

        public CliMock(Mode initialMode) {
            super(initialMode);
            eventHandling_call = 0;
        }

        public boolean eventHandling(String input) {
            eventHandling_call = eventHandling_call+1;
            return true;
        }
    }

//    @Test
//    public void testEventHandling(){
//        CliMock cli = new CliMock(Mode.CALCULATOR);
//        assertNull(cli.parse("help"));
//        assertEquals(1, cli.eventHandling_call);
//    }

    @Test
    public void testMode(){
        cli=new CommandLineInterface(Mode.CALCULATOR);
        assertNull(cli.parse("ConVertor"));
        assertEquals(Mode.CONVERTOR, cli.getMode());
        assertNull(cli.parse("calCulaTor"));
        assertEquals(Mode.CALCULATOR, cli.getMode());
    }
}
