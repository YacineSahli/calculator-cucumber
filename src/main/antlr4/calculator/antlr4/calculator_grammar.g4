// Define a grammar called Hello
grammar calculator_grammar;

//| binaryOp LPAREN expression (SEPARATOR expression)* RPAREN

expression
    : infixExpression
//    | prefixExpression
//    | postfixExpression
    ;

infixExpression
    : infixExpression TIMES infixExpression #OperationMul
    | infixExpression DIV infixExpression #OperationDiv
    | infixExpression PLUS infixExpression #OperationPlus
    | infixExpression MINUS infixExpression #OperationMinus
    | LPAREN infixExpression RPAREN #Parens
    | INTEGER #Integer
    ;

//prefixExpression
//    : binaryOpTimesDiv LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN
//    | binaryOpPlusMinus LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN
//    | variable
//    ;
//
//postfixExpression
//    : LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN binaryOpTimesDiv
//    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN binaryOpPlusMinus
//    | variable
//    ;
//
//booleaninfixExpression
//    : booleaninfixExpression AND booleaninfixExpression
//    | booleaninfixExpression OR booleaninfixExpression
//    | booleaninfixExpression XOR booleaninfixExpression
//    | booleaninfixExpression IMPLICATION booleaninfixExpression
//    | booleaninfixExpression EQUIVALENCE booleaninfixExpression
//    | LPAREN booleaninfixExpression RPAREN
//    | BOOLEAN
//    ;
//booleanprefixExpression
//    : AND LPAREN booleanprefixExpression (SEPARATOR booleanprefixExpression)+ RPAREN
//    | OR LPAREN booleanprefixExpression (SEPARATOR booleanprefixExpression)+ RPAREN
//    | XOR LPAREN booleanprefixExpression (SEPARATOR booleanprefixExpression)+ RPAREN
//    | IMPLICATION LPAREN booleanprefixExpression booleanprefixExpression RPAREN
//    | EQUIVALENCE LPAREN booleanprefixExpression booleanprefixExpression RPAREN
//    | BOOLEAN
//    ;
//booleanpostfixExpression
//    : LPAREN booleanpostfixExpression (SEPARATOR booleanpostfixExpression)+ RPAREN AND
//    | LPAREN booleanpostfixExpression (SEPARATOR booleanpostfixExpression)+ RPAREN OR
//    | LPAREN booleanpostfixExpression (SEPARATOR booleanpostfixExpression)+ RPAREN XOR
//    | LPAREN booleanpostfixExpression booleanpostfixExpression RPAREN IMPLICATION
//    | LPAREN booleanpostfixExpression booleanpostfixExpression RPAREN EQUIVALENCE
//    | BOOLEAN
//    ;
//booleanOperation: AND|OR|XOR|IMPLICATION|EQUIVALENCE;


RATIONAL: INTEGER'/'INTEGER;
INTEGER: ('0' .. '9')+;
BOOLEAN: '0'|'1';
SEPARATOR: '@';

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
AND:'and';
OR:'or';
XOR:'xor';
IMPLICATION:'=>';
EQUIVALENCE:'<=>';

LPAREN: '(';
RPAREN: ')';