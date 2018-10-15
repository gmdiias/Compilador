/**
 * Define a grammar called Hello
 */
grammar Hello;
r  : 'hello' | ID  ;         // match keyword hello followed by an identifier

if_token  : 'if' | ID  ; 

ID : [a-z]+ ;             // match lower-case identifiers

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0-9]+;

// PALAVRAS RESERVADAS
Bool: 'bool';
Def: 'def';
Else : 'else';
False: 'false';
For : 'for';
If : 'if';
Int : 'int';
Read : 'read';
Return : 'return';
Skip : 'skip';
Stop : 'stop';
String : 'string';
True: 'true';
Var: 'var';
While : 'while';
Write: 'write';

//CARACTER ESPECIAIS
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


