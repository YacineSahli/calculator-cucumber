package cli;

import calculator.Expression;
import calculator.antlr4.boolean_grammarLexer;
import calculator.antlr4.boolean_grammarParser;
import org.antlr.v4.runtime.*;

public class BooleanRunner extends Runner {

    public Expression parse(String data){

        CharStream charStream = CharStreams.fromString(data);
        boolean_grammarLexer lex = new boolean_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        boolean_grammarParser parser = new boolean_grammarParser(st);

        lex.removeErrorListeners();
        lex.addErrorListener(lexerBaseErrorListener);

        parser.removeErrorListeners();
        parser.addErrorListener(baseErrorListener);

        BooleanVisitor visitor = new BooleanVisitor();
        return visitor.visit(parser.expression());
    }

    @Override
    public String getHelp() {
        return super.getHelp() ; //todo
    }
}

