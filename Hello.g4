/**
 * Define a grammar called Hello
 */
grammar Hello;
r  : 'hello' | LeftParen | ID | RightParen ;         // match keyword hello followed by an identifier

if_token  : 'if' | LeftParen | ID | RightParen ; 

ID : [a-z]+ ;             // match lower-case identifiers

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0-9]+;

ParenteEsquerdo : '(';
ParenteDireito : ')';
ColcheteEsquerdo : '[';
ColcheteDireto : ']';
ChaveEsquerdo : '{';
ChaveDireito : '}';
Virgula : ',';
PontoVirgula : ';';
Soma : '+';
Subtracao : '-';
Multiplicacao : '*';
Divisao : '/';
RestoDivisao : '%';
Compara : '==';
Diferente : '!=';
Maior : '>';
MaiorIgual : '>=';
Menor : '<';
MenorIgual : '<=';
OuLogico : '||';
ELogico : '&&';
Negacao : '!';
Recebe : '=';
AtribuicaoAdicao : '+=';
AtribuicaoSubtracao : '-=';
AtribuicaoMultiplicacao : '*=';
AtribuicaoDivisao : '/=';
AtribuicaoRestoDivisao : '%=';
Interrogacao : '?';
DoisPontos : ':';
