// Define a grammar called Hello
grammar calculator_grammar;

//| binaryOp LPAREN expression (SEPARATOR expression)* RPAREN

expression
    : expression binaryOp expression # Operation
    | variable                       # Number
    | LPAREN expression RPAREN       # Parents
    ;

binaryOp: TIMES|DIV|PLUS|MINUS;
variable: INTEGER;





// values
RATIONAL: INTEGER'/'INTEGER;
INTEGER: ('0' .. '9')+;

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';

LPAREN: '(';
RPAREN: ')';
