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

                /*"-------------------------------------------------- CALCULATOR HELP --------------------------------------------------\n\n" +
                "This mode allows you to perform arithmetic operations.\n" +
                "Expressions can be written in prefix, infix and postfix notation.\n\n" +
                "In this mode, integers and rational numbers are supported: rational numbers are always noted as follows: 'r<integer>/<integer>'. For example: r5/2.\n\n" +
                "The following operations are available:\n\n" +
                "| Operation       |    Symbol    | Example (infix notation) |\n" +
                "|:----------------|:------------:|:------------------------:|\n" +
                "| PLUS            |     '+'      |           8+2            |\n" +
                "| MINUS           |     '-'      |           5-9            |\n" +
                "| TIMES           |     '*'      |           3*4            |\n" +
                "| DIVISION        |     '/'      |           8/4            |\n" +
                "| MODULAR INVERSE |   'modinv'   |       4 modinv 11        |\n" +
                "| MODULO          | '%' or 'mod' |           5%2            |\n" +
                "| POW             | '^' or 'pow' |           3^2            |\n\n" +
                "The following functions are available.\n" +
                "Note: the functions can only be used with the notation used in the example.\n\n" +
                "| Function | Symbol | Example |\n" +
                "|:---------|:------:|:-------:|\n" +
                "| ABS      | 'abs'  | abs(-2) |\n" +
                "| INV      | 'inv'  | inv(3)  |\n\n" +
                "----------------------------------------------------------------------------------------------------------\n";*/
    }
}
