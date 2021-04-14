package cli;

import java.util.Scanner;

public class CommandLineInterface {

    private boolean is_running;
    private Scanner scanner;
    private Mode mode;

    public CommandLineInterface(Mode initialMode){
        this.mode = initialMode;
        this.scanner = new Scanner(System.in);
        this.is_running = true;
    }

    public void run(){
        System.out.println("\uD835\uDE52\uD835\uDE5A\uD835\uDE61\uD835\uDE58\uD835\uDE64\uD835\uDE62\uD835\uDE5A \uD835\uDE69\uD835\uDE64 \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE3E\uD835\uDE3C\uD835\uDE47\uD835\uDE3E\uD835\uDE50\uD835\uDE47\uD835\uDE3C\uD835\uDE4F\uD835\uDE4A\uD835\uDE4D ❗");
        while(is_running){
            System.out.print("["+mode.toString()+"]> ");
            String input = scanner.nextLine();
            input = input.strip().toUpperCase();
            Mode newMode = isMode(input);
            if(newMode != null){
                this.mode=newMode;
                continue;
            }
            /*if(input.equals("HELP")) {
                displayHelp();
            }*/
            Parser parser = new Parser();
            parser.parse(input);
        }
    }

    public void displayHelp(){
        System.out.println("THE HELP:\ntodo");
    }

    public Mode isMode(String input){
        Mode result = null;
        for (Mode mode :Mode.values()) {
            if(input.equals(mode.toString())){
                result = mode;
                break;
            }
        }
        return result;
    }
}
