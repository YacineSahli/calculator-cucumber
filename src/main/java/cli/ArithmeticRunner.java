package cli;

import calculator.Expression;
import calculator.antlr4.arithmetic_grammarLexer;
import calculator.antlr4.arithmetic_grammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ArithmeticRunner extends Runner {

    public Expression parse(String data) {

        CharStream charStream = CharStreams.fromString(data);
        arithmetic_grammarLexer lex = new arithmetic_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        arithmetic_grammarParser parser = new arithmetic_grammarParser(st);

        lex.removeErrorListeners();
        lex.addErrorListener(lexerBaseErrorListener);

        parser.removeErrorListeners();
        parser.addErrorListener(baseErrorListener);

        ArithmeticVisitor visitor = new ArithmeticVisitor();
        return visitor.visit(parser.expression());
    }

    @Override
    public String getHelp() {
        return super.getHelp() + "\n"  + loadHelpFile("ArithmeticHelper.txt");
    }
}
