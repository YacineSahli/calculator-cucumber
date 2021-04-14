package cli;

import calculator.Expression;
import calculator.antlr4.calculator_grammarBaseVisitor;
import calculator.antlr4.calculator_grammarParser;
import calculator.operation.Plus;
import calculator.variables.IntegerNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrammarVisitor extends calculator_grammarBaseVisitor<Expression> {


    public Expression visitBinaryOp(calculator_grammarParser.ExpressionContext ctx){
        System.out.println("Visit !" + ctx.getText());
        return null;
    }


    public Expression visitVariable(calculator_grammarParser.VariableContext ctx){
        System.out.println("visit variable:"+ctx.getText());
        return null;
    }


    public Expression visitNumber(calculator_grammarParser.NumberContext ctx){
        System.out.println(ctx.getText());
        return null;
        //return new IntegerNumber(Integer.parseInt(ctx.variable().getText()));
    }

    public Expression visitOperation(calculator_grammarParser.OperationContext ctx){
//        List<Expression> params = new ArrayList<>();
//        Collections.addAll(params, visit(ctx.expression(0)), visit(ctx.expression(2)) );
//        return new Plus(params);
//            ctx.op.getText
        visit(ctx.expression(0));
        System.out.println(ctx.binaryOp(0).getText());
        visit(ctx.expression(1));
        return null;
    }


}
