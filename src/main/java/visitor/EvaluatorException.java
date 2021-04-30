package visitor;

import calculator.Expression;

public class EvaluatorException extends Exception {

    private final Expression expression;

    public EvaluatorException(String message, Expression expression) {
        super(message);
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }
}
