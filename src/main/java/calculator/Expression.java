package calculator;

import visitor.Visitor;
import visitor.VisitorException;

public interface Expression {

   void accept(Visitor v) throws VisitorException;

   Integer countDepth(); // counts the depth of nested expressions in an arithmetic expression
   Integer countOps(); // counts the number of operations in an arithmetic expression
   Integer countNbs(); // counts the number of values in an arithmetic expression
}
