package cli;

import calculator.Expression;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public abstract class Runner {

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

    public String getHelp() {
        return
                "-------------------------------------------------- HELP --------------------------------------------------\n\n" +
                        "Available commands:\n\n"+
                        "Modes:\n"+
                        "- calculator\n"+
                        "- boolean\n"+
                        "- convertor\n"+
                        "- date\n"+
                        "\n"+
                        "Commands:\n"+
                        "- history : Display the history of the expressions used recently\n"+
                        "- save [filename] : Save the history in the given filename\n"+
                        "- save [load] : Load the history from the given filename\n"+
                        "- reset : Reset the memory of the calculator\n"+
                        "- s : Save always in the same history.txt file the memory history\n"+
                        "- exit\n"+
                        "- help\n";

    }
}
