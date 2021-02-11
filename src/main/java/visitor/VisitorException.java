package visitor;

import calculator.Expression;

public class VisitorException extends Exception{

    private final Expression expression;

    public VisitorException(String message, Expression expression){
        super(message);
        this.expression = expression;
    }

    public Expression getExpression(){
        return this.expression;
    }
}
