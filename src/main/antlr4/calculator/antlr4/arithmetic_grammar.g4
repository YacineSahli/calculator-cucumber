//Grammar for arithmetic expression
grammar arithmetic_grammar;

expression
    : infixExpression
    | prefixExpression
    | postfixExpression
    ;

infixExpression
    : infixExpression TIMES  infixExpression #OperationInfixMul
    | infixExpression DIV    infixExpression #OperationInfixDiv
    | infixExpression PLUS   infixExpression #OperationInfixPlus
    | infixExpression MINUS  infixExpression #OperationInfixMinus
    | infixExpression MODINV infixExpression #OperationInfixModInv
    | infixExpression MODULO infixExpression #OperationInfixModulo
    | infixExpression POW    infixExpression #OperationInfixPow
    | ABS LPAREN infixExpression RPAREN      #FunctionInfixAbs
    | INV LPAREN infixExpression RPAREN      #FunctionInfixInv
    | LPAREN infixExpression RPAREN          #ParensInfix
    | integer                                #IntegerInfix
    | rational                               #RationalInfix
    ;

prefixExpression
    : TIMES  LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixMul
    | DIV    LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixDiv
    | PLUS   LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixPlus
    | MINUS  LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixMinus
    | MODINV LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixModInv
    | MODULO LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixModulo
    | POW    LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixPow
    | integer                                                             #IntegerPrefix
    | rational                                                            #RationalPrefix
    ;

postfixExpression
    : LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN TIMES  #OperationPostfixMul
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN DIV    #OperationPostfixDiv
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN PLUS   #OperationPostfixPlus
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN MINUS  #OperationPostfixMinus
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN MODINV #OperationPostfixModInv
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN MODULO #OperationPostfixModulo
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN POW    #OperationPostfixPow
    | integer                                                               #IntegerPostfix
    | rational                                                              #RationalPostfix
    ;

//values
rational
    : 'r'INTEGER'/'INTEGER #rationalnumber
    ;

integer
    : INTEGER #integernumber
    ;

INTEGER: ('-')?('0' .. '9')+;

//operation
PLUS:   '+';
MINUS:  '-';
TIMES:  '*';
DIV:    '/';
MODINV: 'modinv';
MODULO: '%'|'mod';
POW:    '^'|'pow';

//functions
ABS: 'abs';
INV: 'inv';

//parentheses
LPAREN: '(';
RPAREN: ')';

//separator for postfix and prefix notation todo
SEPARATOR: ' ';