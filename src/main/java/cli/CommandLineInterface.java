package cli;

import calculator.Calculator;
import calculator.Expression;

import java.util.Locale;
import java.util.Scanner;

public class CommandLineInterface {

    private boolean is_running;
    private Scanner scanner;
    private Mode mode;
    private Calculator c;

    public CommandLineInterface(Mode initialMode){
        this.mode = initialMode;
        this.scanner = new Scanner(System.in);
        this.is_running = true;
        c = new Calculator();
    }

    public void run(){
        System.out.println("\uD835\uDE52\uD835\uDE5A\uD835\uDE61\uD835\uDE58\uD835\uDE64\uD835\uDE62\uD835\uDE5A \uD835\uDE69\uD835\uDE64 \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE3E\uD835\uDE3C\uD835\uDE47\uD835\uDE3E\uD835\uDE50\uD835\uDE47\uD835\uDE3C\uD835\uDE4F\uD835\uDE4A\uD835\uDE4D ❗");
        String input;
        Mode newMode;
        Expression e;
        while(is_running){
            System.out.print("["+mode.toString()+"]> ");
            input = scanner.nextLine();
            newMode = isMode(input);

            if(newMode != null && newMode == Mode.HELP){
                displayHelp();
                continue;
            }else if(newMode != null){
                this.mode=newMode;
                continue;
            }
            try{
                e = mode.parser().parse(input);
                System.out.println(c.eval(e));
            }catch(IllegalStateException exception){
                System.out.println("Invalid expression");
            }
        }
    }

    public void displayHelp(){
        System.out.println("THE HELP:\ntodo");
    }

    public Mode isMode(String input){
        String cleanInput = input.strip().toUpperCase();
        Mode result = null;
        for (Mode mode :Mode.values()) {
            if(cleanInput.equals(mode.toString())){
                result = mode;
                break;
            }
        }
        return result;
    }
}
