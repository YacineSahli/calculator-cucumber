package cli;

import calculator.Calculator;
import calculator.Expression;

import java.security.InvalidParameterException;
import java.util.Locale;
import java.util.Scanner;

public class CommandLineInterface {

    private Scanner scanner;
    private Mode mode;
    private Calculator c;

    public CommandLineInterface(Mode initialMode) {
        this.mode = initialMode;
        this.scanner = new Scanner(System.in);
        c = new Calculator();
    }

    public Mode getMode() {
        return mode;
    }

    public String parse(String input){
        Mode newMode;
        Expression e;
        newMode = isMode(input);
        if (eventHandling(input)){
            return null;
        } else if (newMode != null) {
            this.mode = newMode;
            return null;
        } else {
            try {
                e = mode.parser().parse(input);
                if (e == null && this.mode == Mode.CONVERTOR) {
                    return Double.toString(c.convert(input));
                }
                return c.eval(e).toString();
            } catch (IllegalStateException exception) {//todo should not raise an exception
                return exception.getMessage();
            } catch (InvalidParameterException exception){
                return exception.getMessage();
            }
        }
    }

    public void run() {
        System.out.println("Welcome to the calculator ! \n");
        String input;

        String result="";

        while (true) {
            System.out.print("[" + mode.toString() + "]> ");
            input = scanner.nextLine();
            result = parse(input);
            if(result !=null){
                System.out.println(result);
            }
        }
    }

    public boolean eventHandling(String input) {
        input = input.toLowerCase();
        Expression e;
        boolean result=true;
        switch (input){
            case "j":
                e = c.memory.undo();
                System.out.println(c.memory.getValue(e));
                break;
            case "k":
                e = c.memory.redo();
                System.out.println(c.memory.getValue(e));
                break;
            case "history":
                c.memory.displayLog();
                break;
            case "help":
                System.out.println(this.mode.parser().getHelp());
            case "exit":
                System.out.println("Goodbye !");
                System.exit(0);
                break;
            default: result=false;
        }
        return result;
    }

    public Mode isMode(String input) {
        String cleanInput = input.strip().toUpperCase();
        Mode result = null;
        for (Mode mode : Mode.values()) {
            if (cleanInput.equals(mode.toString())) {
                result = mode;
                break;
            }
        }
        return result;
    }
}
