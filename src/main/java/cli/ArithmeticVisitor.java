package cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.antlr4.arithmetic_grammarBaseVisitor;
import calculator.antlr4.arithmetic_grammarParser;
import calculator.function.Abs;
import calculator.function.Invert;
import calculator.operation.*;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticVisitor extends arithmetic_grammarBaseVisitor<Expression> {
    /*----------------------------------------------------NUMBERS-----------------------------------------------------*/
    public Expression visitRationalnumber(arithmetic_grammarParser.RationalnumberContext ctx) {
        return new RationalNumber(Integer.parseInt(ctx.INTEGER(0).getText()),
                Integer.parseInt(ctx.INTEGER(1).getText()));
    }

    public Expression visitIntegernumber(arithmetic_grammarParser.IntegernumberContext ctx) {
        Integer i = Integer.parseInt(ctx.INTEGER().getText());
        Expression e = new IntegerNumber(i);
        return e;
    }

    /*-----------------------------------------------------INFIX------------------------------------------------------*/
    public Expression visitOperationInfixMul(arithmetic_grammarParser.OperationInfixMulContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixDiv(arithmetic_grammarParser.OperationInfixDivContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixPlus(arithmetic_grammarParser.OperationInfixPlusContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixMinus(arithmetic_grammarParser.OperationInfixMinusContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitParensInfix(arithmetic_grammarParser.ParensInfixContext ctx) {
        return visit(ctx.infixExpression());
    }

    public Expression visitOperationInfixModInv(arithmetic_grammarParser.OperationInfixModInvContext ctx) {
        List<Expression> params = ctx.infixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new ModularInverse(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixModulo(arithmetic_grammarParser.OperationInfixModuloContext ctx) {
        List<Expression> params = ctx.infixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Modulo(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixPow(arithmetic_grammarParser.OperationInfixPowContext ctx) {
        List<Expression> params = ctx.infixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Pow(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitFunctionInfixAbs(arithmetic_grammarParser.FunctionInfixAbsContext ctx) {
        try {
            return new Abs(visit(ctx.infixExpression()));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitFunctionInfixInv(arithmetic_grammarParser.FunctionInfixInvContext ctx) {
        try {
            return new Invert(visit(ctx.infixExpression()));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    /*-----------------------------------------------------PREFIX-----------------------------------------------------*/
    public Expression visitOperationPrefixMul(arithmetic_grammarParser.OperationPrefixMulContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixDiv(arithmetic_grammarParser.OperationPrefixDivContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixPlus(arithmetic_grammarParser.OperationPrefixPlusContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixMinus(arithmetic_grammarParser.OperationPrefixMinusContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixModInv(arithmetic_grammarParser.OperationPrefixModInvContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new ModularInverse(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixModulo(arithmetic_grammarParser.OperationPrefixModuloContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Modulo(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixPow(arithmetic_grammarParser.OperationPrefixPowContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Pow(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    /*----------------------------------------------------POSTFIX-----------------------------------------------------*/
    public Expression visitOperationPostfixMul(arithmetic_grammarParser.OperationPostfixMulContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Times(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixDiv(arithmetic_grammarParser.OperationPostfixDivContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Divides(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixPlus(arithmetic_grammarParser.OperationPostfixPlusContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Plus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixMinus(arithmetic_grammarParser.OperationPostfixMinusContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Minus(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixModInv(arithmetic_grammarParser.OperationPostfixModInvContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new ModularInverse(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixModulo(arithmetic_grammarParser.OperationPostfixModuloContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Modulo(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixPow(arithmetic_grammarParser.OperationPostfixPowContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(i -> visit(i)).collect(Collectors.toList());
        try {
            return new Pow(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }
}
