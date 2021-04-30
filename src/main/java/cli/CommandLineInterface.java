package cli;

import calculator.Calculator;
import calculator.Expression;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class CommandLineInterface {

    private final Scanner scanner;
    private final Calculator c;
    private Mode mode;

    public CommandLineInterface(Mode initialMode) {
        this.mode = initialMode;
        this.scanner = new Scanner(System.in);
        c = new Calculator();
    }

    public Mode getMode() {
        return mode;
    }

    public String parse(String input) {
        Mode newMode;
        Expression e;
        newMode = isMode(input);
        if (eventHandling(input)) {
            return null;
        } else if (isSave(input)) {
            String[] splittedString = input.split(" ");
            c.memory.save(splittedString[1]);
            return null;

        } else if (isLoad(input)) {
            String[] splittedString = input.split(" ");
            c.memory.load(splittedString[1], mode);
            return null;
        } else if (newMode != null) {
            this.mode = newMode;
            return null;
        } else {
            try {
                e = mode.parser().parse(input);
                if (e == null ) {
                    if(this.mode == Mode.CONVERTOR)
                        return Double.toString(c.convert(input));
                    else{
                        throw new IllegalStateException("");
                    }
                }
                Expression res = c.eval(e);
                if(res == null)
                    throw  new IllegalStateException("");
                return res.toString();
            } catch (IllegalStateException | InvalidParameterException exception) {
                return "ERROR " + exception.getMessage();
            }
        }
    }

    public void run() {
        System.out.println("Welcome to the calculator ! \n");
        String input;

        String result;

        while (true) {
            System.out.print("[" + mode.toString() + "]> ");
            input = scanner.nextLine();
            result = parse(input);
            if (result != null) {
                System.out.println(result);
            }
        }
    }

    private boolean eventHandling(String input) {
        input = input.toLowerCase();
        Expression e;
        boolean result = true;
        switch (input) {
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
            case "reset":
                c.memory.reset();
                break;
            case "s":
                c.memory.history();
                break;
            case "help":
                System.out.println(this.mode.parser().getHelp());
                break;
            case "exit":
                System.out.println("Goodbye !");
                System.exit(0);
                break;
            default:
                result = false;
        }
        return result;
    }

    public boolean isSave(String input) {
        String[] splittedString = input.split(" ");
        return input.length() > 3 && input.startsWith("save") && splittedString.length == 2;
    }

    public boolean isLoad(String input) {
        String[] splittedString = input.split(" ");
        return input.length() > 3 && input.startsWith("load") && splittedString.length == 2;
    }

    private Mode isMode(String input) {
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
