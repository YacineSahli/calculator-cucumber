grammar datetime_grammar;

expression
    : infixExpression
    | prefixExpression
    | postfixExpression
    ;

infixExpression
    : DATE PLUS DATE #OperationInfixPlus
    | DATE MINUS DATE #OperationInfixMinus
    | PLUS DATE #OperationInfixPlusUnary
    | DATE PLUS #OperationInfixPlusUnary
    | MINUS DATE #OperationInfixMinusUnary
    | DATE MINUS #OperationInfixMinusUnary
    | DATE #DateInfix
    ;

prefixExpression
    : PLUS LPAREN DATE SEPARATOR? DATE RPAREN #OperationPrefixPlus
    | MINUS LPAREN DATE SEPARATOR? DATE  RPAREN #OperationPrefixMinus
    | DATE #DatePrefix
    ;

postfixExpression
    : LPAREN DATE SEPARATOR? DATE RPAREN PLUS #OperationPostfixPlus
    | LPAREN DATE SEPARATOR? DATE RPAREN MINUS #OperationPostfixMinus
    | DATE #DatePostfix
    ;

DATE
    : YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE TIMESEP SECOND ' ' (AM ' '|PM ' ') TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE ' ' (AM ' '|PM ' ') TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR ' ' (AM ' '|PM ' ') TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE TIMESEP SECOND ' ' TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE ' ' TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR ' ' TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE TIMESEP SECOND ' ' (AM|PM)
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE ' ' (AM|PM)
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR ' ' (AM|PM) TIMEZONE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE TIMESEP SECOND
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR TIMESEP MINUTE
    | YEAR DATESEP MONTH DATESEP DAY ' ' HOUR
    | YEAR DATESEP MONTH DATESEP DAY
    | DURATION
    ;

DURATION: ('P' | 'p') (.)* ('M' | 'm' | 'H' | 'h' | 'S' | 's' | 'D' | 'd' );

//TIMEDURATION
//    : ('T' ('0'..'9')+ ('H'|'h')) | ('T' ('0'..'9')+ ('H'|'h') ('0'..'9')+ ('M'|'m')) | ('T' ('0'..'9')+ ('H'|'h') ('0'..'9')+ ('M'|'m') ('0'..'9')+ ('S'|'s'))
//    | ('T' ('0'..'9')+ ('M'|'m')) | ('T' ('0'..'9')+ ('S'|'s'))
//    | ('T' ('0'..'9')+ ('M'|'m') ('0'..'9')+ ('S'|'s')) | ('T' ('0'..'9')+ ('H'|'h') ('0'..'9')+ ('S'|'s'))
//    ;
//
//DAYDURATION: ((('0'..'9')+ ('D'|'d'))) ;

MINUS: '-';
DATESEP: MINUS;
PLUS: '+';
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
    : 'GMT' ('+' | '-') HOUR TIMESEP MINUTE
    | 'GMT' ('+' | '-') HOUR
    | ('A' .. 'Z')
    | ('A' .. 'Z') ('A' .. 'Z')
    | ('A' .. 'Z') ('A' .. 'Z') ('A' .. 'Z')
    | ('+' | '-') ('0' .. '9') ('0' .. '9') ('0' .. '9') ('0' .. '9')
    ;

SEPARATOR: ','|'@';
WS : [ \t\r\n]+ -> skip ;

