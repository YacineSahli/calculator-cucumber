package cli;

import calculator.Expression;
import calculator.antlr4.boolean_grammarLexer;
import calculator.antlr4.boolean_grammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class BooleanRunner extends Runner {

    public Expression parse(String data) {

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
        return super.getHelp() +
                "-------------------------------------------------- BOOLEAN HELP --------------------------------------------------\n\n"+
                "This mode allows you to perform boolean operations.\n"+
                "Expressions can be written in prefix, infix and postfix notation.\n\n"+
                "In this mode, true is represented by the number 1 and false is represented by the number 0 \n\n"+
                "The following operations are available:\n\n"+
                "| Operation       |    Symbol    | Example (infix notation) |\n"+
                "|:----------------|:------------:|:------------------------:|\n"+
                "| AND             |    'and'     |           1 and 0        |\n"+
                "| OR              |    'or'      |           1 or 0         |\n"+
                "| XOR             |    'xor'     |           1 xor 0        |\n"+
                "| IMPLICATION     |    '=>'      |           1 => 0         |\n"+
                "| EQUIVALENCE     |    '<=>'     |           1 <=> 0        |\n\n"+
                "The following function is available.\n"+
                "Note: the functions can only be used with the notation used in the example.\n\n"+
                "| Function | Symbol | Example |\n"+
                "|:---------|:------:|:-------:|\n"+
                "| NOT      | 'not'  | not(0)  |\n\n"+
                "----------------------------------------------------------------------------------------------------------\n";
    }
}

