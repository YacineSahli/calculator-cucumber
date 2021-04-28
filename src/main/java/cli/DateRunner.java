package cli;

import calculator.Expression;
import calculator.antlr4.datetime_grammarLexer;
import calculator.antlr4.datetime_grammarParser;
import org.antlr.v4.runtime.*;

public class DateRunner extends Runner {

    public Expression parse(String data) {
        CharStream charStream = CharStreams.fromString(data);
        datetime_grammarLexer lex = new datetime_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        datetime_grammarParser parser = new datetime_grammarParser(st);

        lex.removeErrorListeners();
        lex.addErrorListener(lexerBaseErrorListener);

        parser.removeErrorListeners();
        parser.addErrorListener(baseErrorListener);

        DateVisitor visitor = new DateVisitor();
        return visitor.visit(parser.expression());
    }

    @Override
    public String getHelp() {
        return super.getHelp() ;
    }
}
