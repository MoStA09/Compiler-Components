grammar task5;
/*start: (X | Y)+ EOF;
X: (ZERO ONE)+ {System.out.println("A");} ;
Y: (ZERO ONE)+ ONE+ {System.out.println("B");};
ZERO: '0';
ONE: '1';*/
start  : operation |  EOF;
//Tree : NIL | NUMBER+ | (NUMBER+ ',' Tree ',' Tree);
operation
: NIL
| NUMBER+
| (NUMBER','operation','operation)
;
NUMBER : ('0' .. '9') + ('.' ('0' .. '9') +)? ;
WS : [ \ r \ n \ t ] + -> skip;
NIL : 'nil';
//NUMBER : [0-9]+ ;