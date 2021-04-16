package cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.antlr4.arithmetic_grammarBaseVisitor;
import calculator.antlr4.arithmetic_grammarParser;
import calculator.operation.Divides;
import calculator.operation.Minus;
import calculator.operation.Plus;
import calculator.operation.Times;
import calculator.variables.IntegerNumber;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GrammarVisitor extends arithmetic_grammarBaseVisitor<Expression> {



    public Expression visitIntegerInfix(arithmetic_grammarParser.IntegerInfixContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }
    public Expression visitIntegerPrefix(arithmetic_grammarParser.IntegerPrefixContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }
    public Expression visitIntegerPostfix(arithmetic_grammarParser.IntegerPostfixContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }


    public Expression visitOperationInfixMul(arithmetic_grammarParser.OperationInfixMulContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixDiv(arithmetic_grammarParser.OperationInfixDivContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixPlus(arithmetic_grammarParser.OperationInfixPlusContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixMinus(arithmetic_grammarParser.OperationInfixMinusContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixMul(arithmetic_grammarParser.OperationPrefixMulContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixDiv(arithmetic_grammarParser.OperationPrefixDivContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixPlus(arithmetic_grammarParser.OperationPrefixPlusContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixMinus(arithmetic_grammarParser.OperationPrefixMinusContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }


    public Expression visitOperationPostfixMul(arithmetic_grammarParser.OperationPostfixMulContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixDiv(arithmetic_grammarParser.OperationPostfixDivContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixPlus(arithmetic_grammarParser.OperationPostfixPlusContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixMinus(arithmetic_grammarParser.OperationPostfixMinusContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }


    public Expression visitParensInfix(arithmetic_grammarParser.ParensInfixContext ctx){
        return visit((ParseTree) ctx.infixExpression());
    }
}
