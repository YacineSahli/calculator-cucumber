package cli;

import calculator.Expression;
import calculator.antlr4.arithmetic_grammarLexer;
import calculator.antlr4.arithmetic_grammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class ArithmeticParser implements Parser{

    public Expression parse(String data){

        CharStream charStream = CharStreams.fromString(data);
        arithmetic_grammarLexer lex = new arithmetic_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        arithmetic_grammarParser parser = new arithmetic_grammarParser(st);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        ArithmeticVisitor visitor = new ArithmeticVisitor();
        return visitor.visit(parser.expression());
    }
}
