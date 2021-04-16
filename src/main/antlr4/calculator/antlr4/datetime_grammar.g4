grammar datetime_grammar;

expression
    : infixExpression
    | prefixExpression
    | postfixExpression
    ;

infixExpression
    : DATE PLUS DATE #OperationInfixPlus
    | DATE MINUS DATE #OperationInfixMinus
    | DATE SPACE PLUS SPACE DATE #OperationInfixPlus
    | DATE SPACE MINUS SPACE DATE #OperationInfixMinus
    | PLUS DATE #OperationInfixPlusUnary
    | PLUS SPACE DATE #OperationInfixPlusUnary
    | DATE PLUS #OperationInfixPlusUnary
    | DATE SPACE PLUS #OperationInfixPlusUnary
    | MINUS DATE #OperationInfixMinusUnary
    | MINUS SPACE DATE #OperationInfixMinusUnary
    | DATE MINUS #OperationInfixMinusUnary
    | DATE SPACE MINUS #OperationInfixMinusUnary
    | MINUS #DateInfix
    ;

prefixExpression
    : PLUS LPAREN DATE SPACE DATE RPAREN #OperationPrefixPlus
    | MINUS LPAREN DATE SPACE DATE  RPAREN #OperationPrefixMinus
    | DATE #DatePrefix
    ;

postfixExpression
    : LPAREN DATE SPACE DATE RPAREN PLUS #OperationPostfixPlus
    | LPAREN DATE SPACE DATE RPAREN MINUS #OperationPostfixMinus
    | DATE #DatePostfix
    ;

DATE
    : YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE TIMESEP SECOND SPACE (AM SPACE|PM SPACE) TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE SPACE (AM SPACE|PM SPACE) TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR SPACE (AM SPACE|PM SPACE) TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE TIMESEP SECOND SPACE TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE SPACE TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR SPACE TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE TIMESEP SECOND SPACE (AM|PM)
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE SPACE (AM|PM)
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR SPACE (AM|PM) TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE TIMESEP SECOND
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR TIMESEP MINUTE
    | YEAR DATESEP MONTH DATESEP DAY SPACE HOUR
    | YEAR DATESEP MONTH DATESEP DAY
    | DURATION
    ;

DURATION
    : ('+' | '-') ('P' | 'p') (.)*
    | ('P' | 'p') (.)*
    ;

//TIMEDURATION
//    : ('T' ('0'..'9')+ ('H'|'h')) | ('T' ('0'..'9')+ ('H'|'h') ('0'..'9')+ ('M'|'m')) | ('T' ('0'..'9')+ ('H'|'h') ('0'..'9')+ ('M'|'m') ('0'..'9')+ ('S'|'s'))
//    | ('T' ('0'..'9')+ ('M'|'m')) | ('T' ('0'..'9')+ ('S'|'s'))
//    | ('T' ('0'..'9')+ ('M'|'m') ('0'..'9')+ ('S'|'s')) | ('T' ('0'..'9')+ ('H'|'h') ('0'..'9')+ ('S'|'s'))
//    ;
//
//DAYDURATION: ((('0'..'9')+ ('D'|'d'))) ;




DATESEP: MINUS;
PLUS: '+';
MINUS: '-';
SPACE: ' ';
TIMESEP: ':';
LPAREN: '(';
RPAREN: ')';

PM: 'PM' | 'pm';
AM: 'AM' | 'am';

YEAR: ('0' .. '9') ('0' .. '9') ('0' .. '9') ('0' .. '9') ;
MONTH: ('0' .. '1')('0' .. '9');
DAY: ('0' .. '3') ('0' .. '9');
HOUR
    : ('0' .. '2') ('0' .. '9')
    | ('0' .. '9')
    ;
MINUTE: ('0' .. '6') ('0' .. '9');
SECOND: ('0' .. '6') ('0' .. '9');

TIMEZONE
    : 'GMT' ('+' | '-') HOUR DATESEP MINUTE
    | 'GMT' ('+' | '-') HOUR
    | ('A' .. 'Z')
    | ('A' .. 'Z') ('A' .. 'Z')
    | ('A' .. 'Z') ('A' .. 'Z') ('A' .. 'Z')
    | ('+' | '-') ('0' .. '9') ('0' .. '9') ('0' .. '9') ('0' .. '9')
    ;
