package cli;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.antlr4.boolean_grammarBaseVisitor;
import calculator.antlr4.boolean_grammarParser;
import calculator.function.Not;
import calculator.operation.*;
import calculator.variables.MyBoolean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanVisitor extends boolean_grammarBaseVisitor<Expression> {

    public Expression visitBooleannumber(boolean_grammarParser.BooleannumberContext ctx) {
        return new MyBoolean(Integer.parseInt(ctx.BOOLEAN().getText()));
    }

    public Expression visitOperationInfixAnd(boolean_grammarParser.OperationInfixAndContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new And(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixOr(boolean_grammarParser.OperationInfixOrContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Or(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixXor(boolean_grammarParser.OperationInfixXorContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Xor(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixImplication(boolean_grammarParser.OperationInfixImplicationContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Implication(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationInfixEquivalence(boolean_grammarParser.OperationInfixEquivalenceContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.infixExpression(0)), visit(ctx.infixExpression(1)));
        try {
            return new Equivalence(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitParensInfix(boolean_grammarParser.ParensInfixContext ctx) {
        return visit(ctx.infixExpression());
    }

    public Expression visitFunctionInfixNot(boolean_grammarParser.FunctionInfixNotContext ctx) {
        Expression param = visit(ctx.infixExpression());
        try {
            return new Not(param);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixAnd(boolean_grammarParser.OperationPrefixAndContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new And(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixOr(boolean_grammarParser.OperationPrefixOrContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Or(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixXor(boolean_grammarParser.OperationPrefixXorContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Xor(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixImplication(boolean_grammarParser.OperationPrefixImplicationContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Implication(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPrefixEquivalence(boolean_grammarParser.OperationPrefixEquivalenceContext ctx) {
        List<Expression> params = ctx.prefixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Equivalence(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixAnd(boolean_grammarParser.OperationPostfixAndContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new And(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixOr(boolean_grammarParser.OperationPostfixOrContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Or(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixXor(boolean_grammarParser.OperationPostfixXorContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Xor(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixImplication(boolean_grammarParser.OperationPostfixImplicationContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Implication(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    public Expression visitOperationPostfixEquivalence(boolean_grammarParser.OperationPostfixEquivalenceContext ctx) {
        List<Expression> params = ctx.postfixExpression().stream().map(this::visit).collect(Collectors.toList());
        try {
            return new Equivalence(params);
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }
}
