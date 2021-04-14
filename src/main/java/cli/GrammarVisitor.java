package cli;

import calculator.antlr4.calculator_grammarBaseVisitor;
import calculator.antlr4.calculator_grammarParser;

public class GrammarVisitor extends calculator_grammarBaseVisitor {

    public String visitR(calculator_grammarParser.RContext ctx) {
        System.out.println("Visit !");
        return null;
    }
}
