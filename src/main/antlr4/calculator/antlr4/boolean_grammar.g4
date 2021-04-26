grammar boolean_grammar;

expression
    : infixExpression
    | postfixExpression
    | prefixExpression
    ;

infixExpression
    : infixExpression AND infixExpression #OperationInfixAnd
    | infixExpression OR infixExpression #OperationInfixOr
    | infixExpression XOR infixExpression #OperationInfixXor
    | infixExpression IMPLICATION infixExpression #OperationInfixImplication
    | infixExpression EQUIVALENCE infixExpression #OperationInfixEquivalence
    | NOT LPAREN infixExpression RPAREN #FunctionInfixNot
    | LPAREN infixExpression RPAREN #ParensInfix
    | bool #BooleanInfix
    ;
prefixExpression
    : AND LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixAnd
    | OR LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixOr
    | XOR LPAREN prefixExpression (SEPARATOR prefixExpression)+ RPAREN #OperationPrefixXor
    | IMPLICATION LPAREN prefixExpression prefixExpression RPAREN #OperationPrefixImplication
    | EQUIVALENCE LPAREN prefixExpression prefixExpression RPAREN #OperationPrefixEquivalence
    | bool #BooleanPrefix
    ;
postfixExpression
    : LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN AND #OperationPostfixAnd
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN OR #OperationPostfixOr
    | LPAREN postfixExpression (SEPARATOR postfixExpression)+ RPAREN XOR #OperationPostfixXor
    | LPAREN postfixExpression postfixExpression RPAREN IMPLICATION #OperationPostfixImplication
    | LPAREN postfixExpression postfixExpression RPAREN EQUIVALENCE #OperationPostfixEquivalence
    | bool #BooleanPostfix
    ;
bool:
    BOOLEAN #Booleannumber
    ;
BOOLEAN: '0'|'1';

AND:'and';
OR:'or';
XOR:'xor';
IMPLICATION:'=>';
EQUIVALENCE:'<=>';
NOT: 'not';

LPAREN: '(';
RPAREN: ')';

SEPARATOR: ' ';













