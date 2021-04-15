package cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.antlr4.calculator_grammarBaseVisitor;
import calculator.antlr4.calculator_grammarParser;
import calculator.operation.Divides;
import calculator.operation.Minus;
import calculator.operation.Plus;
import calculator.operation.Times;
import calculator.variables.IntegerNumber;
import calculator.variables.MyBoolean;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GrammarVisitor extends calculator_grammarBaseVisitor<Expression> {



    public Expression visitIntegerInfix(calculator_grammarParser.IntegerInfixContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }
    public Expression visitIntegerPrefix(calculator_grammarParser.IntegerPrefixContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }
    public Expression visitIntegerPostfix(calculator_grammarParser.IntegerPostfixContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }


    public Expression visitOperationInfixMul(calculator_grammarParser.OperationInfixMulContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixDiv(calculator_grammarParser.OperationInfixDivContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixPlus(calculator_grammarParser.OperationInfixPlusContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixMinus(calculator_grammarParser.OperationInfixMinusContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixMul(calculator_grammarParser.OperationPrefixMulContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixDiv(calculator_grammarParser.OperationPrefixDivContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixPlus(calculator_grammarParser.OperationPrefixPlusContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixMinus(calculator_grammarParser.OperationPrefixMinusContext ctx){
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }


    public Expression visitOperationPostfixMul(calculator_grammarParser.OperationPostfixMulContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixDiv(calculator_grammarParser.OperationPostfixDivContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixPlus(calculator_grammarParser.OperationPostfixPlusContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixMinus(calculator_grammarParser.OperationPostfixMinusContext ctx){
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }


    public Expression visitParensInfix(calculator_grammarParser.ParensInfixContext ctx){
        return visit((ParseTree) ctx.infixExpression());
    }
}
