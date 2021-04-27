package cli;

import calculator.Calculator;
import calculator.Expression;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class CommandLineInterface {

    private boolean is_running;
    private Scanner scanner;
    private Mode mode;
    private Calculator c;

    public CommandLineInterface(Mode initialMode) {
        this.mode = initialMode;
        this.scanner = new Scanner(System.in);
        this.is_running = true;
        c = new Calculator();
    }

    public void run() {
        System.out.println("Welcome to the calculator ! \n");
        String input;
        Mode newMode;
        Expression e;
        while (is_running) {
            System.out.print("[" + mode.toString() + "]> ");
            input = scanner.nextLine();
            newMode = isMode(input);
            if (eventHandling(input)){
                continue;
            }
            else if (newMode != null && newMode == Mode.HELP) {
                System.out.println(this.mode.parser().getHelp());
                continue;
            } else if (newMode != null) {
                this.mode = newMode;
                continue;
            } else {
                try {
                    e = mode.parser().parse(input);
                    if (e == null && this.mode == Mode.CONVERTOR) {
                        System.out.println((c.convert(input)));
                        continue;
                    }
                    System.out.println(c.eval(e));
                } catch (IllegalStateException exception) {
                    System.out.println("Invalid expression");
                } catch (InvalidParameterException exception){
                    System.out.println(exception.getMessage());
                }
            }

        }
    }

    public boolean eventHandling(String input) {
        Expression e;
        if (input.equals("j")) {
            e = c.memory.undo();
            System.out.println(c.memory.getValue(e));
            return true;
        } else if (input.equals("k")) {
            e = c.memory.redo();
            System.out.println(c.memory.getValue(e));
            return true;
        } else if (input.equals("history")) {
            c.memory.displayLog();
            return true;
        }
        else if(input.equals("exit")){
            System.exit(0);
            return true;
        }
        return false;
    }
    /*
    public void displayHelp(){
        System.out.println("THE HELP:\ntodo");
    }
    */

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
