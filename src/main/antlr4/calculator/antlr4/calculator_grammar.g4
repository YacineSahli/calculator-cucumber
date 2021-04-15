// Define a grammar called Hello
grammar calculator_grammar;

expression
    : infixExpression
    | prefixExpression
    | postfixExpression
    ;

infixExpression
    : infixExpression TIMES infixExpression #OperationInfixMul
    | infixExpression DIV infixExpression #OperationInfixDiv
    | infixExpression PLUS infixExpression #OperationInfixPlus
    | infixExpression MINUS infixExpression #OperationInfixMinus
    | LPAREN infixExpression RPAREN #ParensInfix
    | INTEGER #IntegerInfix
    ;

prefixExpression
    : TIMES LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixMul
    | DIV LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixDiv
    | PLUS LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixPlus
    | MINUS LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixMinus
    | INTEGER #IntegerPrefix
    ;

postfixExpression
    : LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN TIMES #OperationPostfixMul
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN DIV #OperationPostfixDiv
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN PLUS #OperationPostfixPlus
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN MINUS #OperationPostfixMinus
    | INTEGER #IntegerPostfix
    ;


RATIONAL: INTEGER'/'INTEGER;
INTEGER: ('0' .. '9')+;
BOOLEAN: '0'|'1';
SEPARATOR: ' ';

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';

LPAREN: '(';
RPAREN: ')';