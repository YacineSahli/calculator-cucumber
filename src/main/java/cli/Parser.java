package cli;

import calculator.Expression;
import calculator.antlr4.calculator_grammarLexer;
import calculator.antlr4.calculator_grammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Parser {

    public Expression parse(String data){

        CharStream charStream = CharStreams.fromString(data);
        calculator_grammarLexer lex = new calculator_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        calculator_grammarParser parser = new calculator_grammarParser(st);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        GrammarVisitor visitor = new GrammarVisitor();
        return visitor.visit(parser.expression());
    }
}
