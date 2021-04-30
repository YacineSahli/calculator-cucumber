package cli;

import calculator.Expression;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Runner {
    protected Scanner scanner;

    public static final BaseErrorListener baseErrorListener = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            throw new IllegalStateException("", e);
        }
    };

    public static final BaseErrorListener lexerBaseErrorListener = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
        }
    };


    public abstract Expression parse(String data);
    public String loadHelpFile(String filename){
        String help = "";
        try {
            scanner = new Scanner(new File("help/"+filename));
            while (scanner.hasNextLine()){
                help += scanner.nextLine()+"\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Help file not found");
        }
        return help;
    }
    public String getHelp() {
        return loadHelpFile("Help.txt");

    }
}
