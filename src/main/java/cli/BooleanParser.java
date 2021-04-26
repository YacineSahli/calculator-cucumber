package cli;

import calculator.Expression;
import calculator.antlr4.boolean_grammarLexer;
import calculator.antlr4.boolean_grammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class BooleanParser implements Parser{

    public Expression parse(String data){

        CharStream charStream = CharStreams.fromString(data);
        boolean_grammarLexer lex = new boolean_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        boolean_grammarParser parser = new boolean_grammarParser(st);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        BooleanVisitor visitor = new BooleanVisitor();
        return visitor.visit(parser.expression());
    }

    @Override
    public String getHelp() {
        return "some help"; //todo
    }
}

