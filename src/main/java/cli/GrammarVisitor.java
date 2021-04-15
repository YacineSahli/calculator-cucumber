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

public class GrammarVisitor extends calculator_grammarBaseVisitor<Expression> {



    public Expression visitInteger(calculator_grammarParser.IntegerContext ctx){
        return new IntegerNumber(Integer.parseInt(ctx.getText()));
    }

//    public Expression visitBoolean(calculator_grammarParser.BooleanContext ctx){
//        return new MyBoolean(Integer.parseInt(ctx.getText()));
//    }


    public Expression visitOperationMul(calculator_grammarParser.OperationMulContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationDiv(calculator_grammarParser.OperationDivContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPlus(calculator_grammarParser.OperationPlusContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationMinus(calculator_grammarParser.OperationMinusContext ctx){
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)) );
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitParens(calculator_grammarParser.ParensContext ctx){
        return visit((ParseTree) ctx.infixExpression());
    }
}
