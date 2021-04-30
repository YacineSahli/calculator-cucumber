package cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.antlr4.datetime_grammarBaseVisitor;
import calculator.antlr4.datetime_grammarParser;
import calculator.function.MinusFunction;
import calculator.function.PlusFunction;
import calculator.operation.Minus;
import calculator.operation.Plus;
import calculator.variables.MyTime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DateVisitor extends datetime_grammarBaseVisitor<Expression> {

    public Expression visitOperationInfixPlus(datetime_grammarParser.OperationInfixPlusContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            Collections.addAll(params, new MyTime(ctx.DATE(0).getText()), new MyTime(ctx.DATE(1).getText()));
            return new Plus(params);
        } catch (IllegalConstruction | ParseException e) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixMinus(datetime_grammarParser.OperationInfixMinusContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            Collections.addAll(params, new MyTime(ctx.DATE(0).getText()), new MyTime(ctx.DATE(1).getText()));
            return new Minus(params);
        } catch (IllegalConstruction | ParseException e) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixPlusUnary(datetime_grammarParser.OperationInfixPlusUnaryContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            return new PlusFunction( new MyTime(ctx.DATE().getText()));
        } catch (IllegalConstruction | ParseException e) {
            throw new IllegalStateException();
        }
    }

    public Expression visitOperationInfixMinusUnary(datetime_grammarParser.OperationInfixMinusUnaryContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            return new MinusFunction( new MyTime(ctx.DATE().getText()));
        } catch (IllegalConstruction | ParseException e) {
            throw new IllegalStateException();
        }
    }


    public Expression visitOperationPrefixPlus(datetime_grammarParser.OperationPrefixPlusContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            Collections.addAll(params, new MyTime(ctx.DATE(0).getText()), new MyTime(ctx.DATE(1).getText()));
            return new Plus(params);
        } catch (IllegalConstruction | ParseException e) {
            throw new IllegalStateException();
        }
    }

    public Expression visitOperationPrefixMinus(datetime_grammarParser.OperationPrefixMinusContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            Collections.addAll(params, new MyTime(ctx.DATE(0).getText()), new MyTime(ctx.DATE(1).getText()));
            return new Minus(params);
        } catch (IllegalConstruction | ParseException e) {
            throw new IllegalStateException();
        }
    }


    public Expression visitOperationPostfixPlus(datetime_grammarParser.OperationPostfixPlusContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            Collections.addAll(params, new MyTime(ctx.DATE(0).getText()), new MyTime(ctx.DATE(1).getText()));
            return new Plus(params);
        } catch (IllegalConstruction | ParseException e) {
            throw new IllegalStateException();
        }
    }

    public Expression visitOperationPostfixMinus(datetime_grammarParser.OperationPostfixMinusContext ctx) {
        List<Expression> params = new ArrayList<>();
        try {
            Collections.addAll(params, new MyTime(ctx.DATE(0).getText()), new MyTime(ctx.DATE(1).getText()));
            return new Minus(params);
        } catch (IllegalConstruction | ParseException e) {
            throw new IllegalStateException();
        }
    }

}