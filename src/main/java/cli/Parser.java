package cli;

import calculator.antlr4.calculator_grammarLexer;
import calculator.antlr4.calculator_grammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Parser {

    public static void parse(){
        CharStream charStream = CharStreams.fromString("Hello Jean");
        calculator_grammarLexer lex = new calculator_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        calculator_grammarParser parser = new calculator_grammarParser(st);

        GrammarVisitor visitor = new GrammarVisitor();
        visitor.visitR(parser.r());
        //parser.r().
    }


}
