grammar Grace;

grace: PROGRAMA decVar END EOF;  

decVar :
    VAR
    listaSpecVar
    DOISPONTOS
    tiposPrimitivos
    PONTOVIRGULA
    ;
      
listaSpecVar :
	specVar
	(VIRGULA specVar)*
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
	
	
// ----- DECLARACAO DE SUBPROGRAMAS -----

decSub:
	decProc
	| decFunc
	;
      
// DECLARACAO DE PROCEDIMENTO      
decProc: 
	DEF
	IDENTIFICADOR
	PARENTEESQUERDO
	listaParametros
	PARENTEDIREITO
	bloco
	;

// DECLARACAO DE FUNCAO 
decFunc:
	DEF
	IDENTIFICADOR
	PARENTEESQUERDO
	listaParametros
	PARENTEDIREITO
	DOISPONTOS
	tiposPrimitivos
	bloco
	;

// DECLARACAO DE BLOCO
bloco:
	CHAVEESQUERDO
	// TODO DEC
	CHAVEDIREITO
	;

// DECLARACAO DE LISTA DE PARAMETROS
listaParametros:
	specParams
	(PONTOVIRGULA specParams)*
	;
	
specParams:
	param
	(VIRGULA param)*
	DOISPONTOS
    tiposPrimitivos
    ;	
	
param: 
	IDENTIFICADOR
	| IDENTIFICADOR
		COLCHETEESQUERDO
		COLCHETEDIREITO
	; 
	
// TODO DECLARACAO ANINHADA DE SUBPROGRAMAS
	
// ----- DECLARACAO DE COMANDOS -----
cmdRead:
	READ
	// TODO VARIAVEL
	PONTOVIRGULA
	;

cmdWrite:
	WRITE
	// TODO EXPRESSAO
	(VIRGULA )* // EXPRESSAO
	PONTOVIRGULA
	;

// ----- DECLARACAO DE COMENTARIOS -----
e_Comentario : 
	COMENTARIO
	*;
      
tiposPrimitivos : decInt | TIPOBOOL | decString;
tipoBool : TRUE | FALSE;
tipoNumero : NUMERO |
             CHAVEESQUERDO
			 NUMERO
			 (VIRGULA NUMERO)*
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

WRITE : 'write';
READ : 'read';
DEF : 'def';
VAR : 'var';
PROGRAMA : 'programa';
END : 'end';

IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]* ;
NUMERO : [0-9]*;
STRING : [a-zA-Z_]?;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
