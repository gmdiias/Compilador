grammar Grace;

grace: 'programa' decVar 'end' EOF;  

decVar :
      'var'
      listaSpecVar
      DOISPONTOS
      tiposPrimitivos
      PONTOVIRGULA
      ;
      
listaSpecVar :
	  specVar
	  (',' specVar)*
	  ;     
	   
specVar:
      specVarSimples|
      specVarSimplesIni
      //specVarArranjo|
      //specVarArranjoIni
      ;

specVarSimples:
	  IDENTIFICADOR
      ;

specVarSimplesIni:      
      IDENTIFICADOR
      RECEBE
      (tipoNumero|STRING|tipoBool)
      ;
            
e_Comentario : 
	COMENTARIO
	*;
      
tiposPrimitivos : decInt | TIPOBOOL | decString;
tipoBool : TRUE | FALSE;
tipoNumero : NUMERO |
             CHAVEESQUERDO
			 NUMERO
			 (',' NUMERO)*
			 CHAVEDIREITO
			 ;
			
decInt: TIPOINT |
		TIPOINT
		COLCHETEESQUERDO
	    NUMERO
		COLCHETEDIREITO;
		
decString : TIPOSTRING |
			TIPOSTRING 
			COLCHETEESQUERDO
			NUMERO
			COLCHETEDIREITO;

// TOKENS
TIPOINT : 'int';
TIPOSTRING : 'string';
PARENTEESQUERDO : '(';
PARENTEDIREITO : ')';
COLCHETEESQUERDO : '[';
COLCHETEDIREITO : ']';
CHAVEESQUERDO : '{';
CHAVEDIREITO : '}';
VIRGULA : ',';
PONTOVIRGULA : ';';
SOMA : '+';
SUBTRACAO : '-';
MULTIPLICACAO : '*';
DIVISAO : '/';
RESTODIVISAO : '%';
COMPARA : '==';
DIFERENTE : '!=';
MAIOR : '>';
MAIORIGUAL : '>=';
MENOR : '<';
MENORIGUAL : '<=';
OULOGICO : '||';
ELOGICO : '&&';
NEGACAO : '!';
RECEBE : '=';
ATRIBUICAOSOMA : '+=';
ATRIBUICAOSUBTRACAO : '-=';
ATRIBUICAOMULTIPLICACAO : '*=';
ATRIBUICAODIVISAO : '/=';
ATRIBUICAORESTODIVISAO : '%=';
INTERROGACAO : '?';
DOISPONTOS : ':';   
COMENTARIO : '//';
TRUE : 'true';
FALSE : 'false';
TIPOBOOL : 'bool';

IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]* ;
NUMERO : [0-9]*;
STRING : [a-zA-Z_]?;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
