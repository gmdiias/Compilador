grammar FGM;

fgm: 'programa' t_Atibuicao 'end' EOF;

t_Atibuicao : 
      t_TiposPrimitivos 
      Identificador 
      (t_Recebe 
      (Numero|String))*
      t_PontoVirgula;
      
t_TiposPrimitivos : 'int'|'bool'|'string';
t_ParenteEsquerdo : '(';
t_ParenteDireito : ')';
t_ColcheteEsquerdo : '[';
t_ColcheteDireto : ']';
t_ChaveEsquerdo : '{';
t_ChaveDireito : '}';
t_Virgula : ',';
t_PontoVirgula : ';';
t_Soma : '+';
t_Subtracao : '-';
t_Multiplicacao : '*';
t_Divisao : '/';
t_RestoDivisao : '%';
t_Compara : '==';
t_Diferente : '!=';
t_Maior : '>';
t_MaiorIgual : '>=';
t_Menor : '<';
t_MenorIgual : '<=';
t_OuLogico : '||';
t_ELogico : '&&';
t_Negacao : '!';
t_Recebe : '=';
t_AtribuicaoAdicao : '+=';
t_AtribuicaoSubtracao : '-=';
t_AtribuicaoMultiplicacao : '*=';
t_AtribuicaoDivisao : '/=';
t_AtribuicaoRestoDivisao : '%=';
t_Interrogacao : '?';
t_DoisPontos : ':';   

Identificador : [a-zA-Z_][a-zA-Z0-9_]* ;
Numero : [0-9]*;
String : [a-zA-Z_]?;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
