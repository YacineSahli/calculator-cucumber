package calculator;

import visitor.Visitor;
import visitor.EvaluatorException;

public interface Expression {
   void accept(Visitor v) throws EvaluatorException;

   Integer countDepth(); // counts the depth of nested expressions in an arithmetic expression
   Integer countOps(); // counts the number of operations in an arithmetic expression
   Integer countNbs(); // counts the number of values in an arithmetic expression
}
