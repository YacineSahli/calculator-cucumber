package cli;

import calculator.Expression;
import calculator.antlr4.datetime_grammarLexer;
import calculator.antlr4.datetime_grammarParser;
import org.antlr.v4.runtime.*;

public class DateParser {

    public Expression parse(String data){

        CharStream charStream = CharStreams.fromString(data);
        datetime_grammarLexer lex = new datetime_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        datetime_grammarParser parser = new datetime_grammarParser(st);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        DateVisitor visitor = new DateVisitor();
        return visitor.visit(parser.expression());
    }
}
