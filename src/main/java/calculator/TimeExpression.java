package calculator;

import visitor.EvaluatorException;
import visitor.TimeVisitor;
import visitor.Visitor;

public interface TimeExpression {
    void accept(TimeVisitor v) throws EvaluatorException;

    Integer countDepth(); // counts the depth of nested expressions in an arithmetic expression
    Integer countOps(); // counts the number of operations in an arithmetic expression
    Integer countNbs(); // counts the number of values in an arithmetic expression
}
