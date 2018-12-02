grammar Grace;

@header {

}

@members {
	
}

grace: decVar+ cmdIf+ decVar?;

// --------- TODO DECLARACAO DE VARIAVEL ------------ //
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
    ;

specVarSimples //returns [String iden = $IDENTIFICADOR.text]:
	:IDENTIFICADOR
    ;

specVarSimplesIni:      
    IDENTIFICADOR
    RECEBE
    decExpressao
    ;
            
specVarArranjo:
	IDENTIFICADOR
	COLCHETEESQUERDO
	NUMERO;	
	
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
	( decSub | 
	  decVar )+
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

comando:
	cmdSimples |
	bloco;
	
cmdSimples:
	cmdAtrib |
	cmdRead |
	cmdWrite
	;

cmdAtrib:
	atrib
	PONTOVIRGULA
	;
	
atrib:
	IDENTIFICADOR
	( 	ATRIBUICAOSOMA |
		ATRIBUICAOSUBTRACAO |
		ATRIBUICAOMULTIPLICACAO |
		ATRIBUICAODIVISAO |
		ATRIBUICAORESTODIVISAO )
	decExpressao
	;

cmdRead:
	READ
	// TODO VARIAVEL
	PONTOVIRGULA
	;

cmdWrite:
	WRITE
	// TODO EXPRESSAO
	(VIRGULA )* // EXPRESSAO
	;
	
// ----- CONDICIONAL IF -----
cmdIf: 
	IDIF
	PARENTEESQUERDO
	(decExpressaoRelacional)
	PARENTEDIREITO
	comando
	cmdElse
	;
	
cmdElse: 
	(IDELSE comando)?
;

// ---- CLAÇO WHILE -----
cmdWhile: 
	IDWHILE
	PARENTEESQUERDO
	decExpressao
	PARENTEDIREITO
	comando
;

// ----- LAÇO FOR -----
cmdFor: 
	IDFOR
	PARENTEESQUERDO
	atribIni
	PONTOVIRGULA
	decExpressao
	PONTOVIRGULA
	atribPasso
	PARENTEDIREITO
	comando
;

atribIni: 
	specVarSimplesIni
;

atribPasso:
	specVarSimples
	operador
	valor
	;
	
// --- Interrupção do Laço
cmdStop:
	IDSTOP
	PONTOVIRGULA
;

// -- Salto de Interação
//TODO
	
// ----- DECLARACAO DE EXPRESSAO -----
decExpressao: 
	decExpressao operador decExpressao
//	|decExpressao '?' decExpressao ':' decExpressao
    |valor
    ;
	  
valor: IDENTIFICADOR
	 | (NUMERO|STRING|BOOLEAN)
     | PARENTEESQUERDO decExpressao PARENTEDIREITO
     ;

operador:
	SOMA
	|SUBTRACAO
	|MULTIPLICACAO
	|DIVISAO
	;

// ----- DECLARACAO DE EXPRESSAO RELACIONAL-----
decExpressaoRelacional:
	 decExpressaoRelacional operadorRelacional decExpressaoRelacional
	 |valorRelacional;

valorRelacional:
	  IDENTIFICADOR
	  |NUMERO
	  |PARENTEESQUERDO decExpressao PARENTEDIREITO;
	  
operadorRelacional:
	MAIOR
	|MAIORIGUAL
	|MENOR
	|MENORIGUAL
	|COMPARA
	|DIFERENTE;

// ----- DECLARACAO DE EXPRESSAO IGUALDADE-----
decExpressaoIgualdade:
	 decExpressaoIgualdade operadorIgualdade decExpressaoIgualdade
	 |valorIgualdade;

valorIgualdade:
	  IDENTIFICADOR
	  |(NUMERO|STRING|BOOLEAN)
	  |PARENTEESQUERDO decExpressaoIgualdade PARENTEDIREITO;
	  
operadorIgualdade:
	COMPARA
	|DIFERENTE;

// ----- DECLARACAO DE EXPRESSAO LOGICA-----
decExpressaoLogica:
	 decExpressaoLogica operadorLogica decExpressaoLogica
	 |valorLogica;

valorLogica:
	  IDENTIFICADOR
	  |BOOLEAN
	  |PARENTEESQUERDO decExpressaoLogica PARENTEDIREITO;
	  
operadorLogica:
	OULOGICO
	|ELOGICO ;	
// ----- DECLARACAO DE COMENTARIOS -----
e_Comentario : 
	COMENTARIO
	*;
	
// ----- DECLARACAO DE TIPOS -----
      
tiposPrimitivos : decInt | TIPOBOOL | decString;
	
tipoNumero : 
	NUMERO;

tipoArranjo :
    CHAVEESQUERDO
	NUMERO
	(VIRGULA NUMERO)*
	CHAVEDIREITO
	;
			
decInt : 
	TIPOINT |
	TIPOINT
	COLCHETEESQUERDO
	NUMERO
	COLCHETEDIREITO
	;
		
decString :
 	TIPOSTRING |
	TIPOSTRING 
	COLCHETEESQUERDO
	NUMERO
	COLCHETEDIREITO
	;
			
// ---------TODO DECLARACAO DE VARIAVE FIM------------ //
	
// TOKENS
BOOLEAN : 
	TRUE 
	| FALSE
	;
	
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
IDSTOP: 'stop';

IDIF: 'if';
IDELSE: 'else';
IDWHILE: 'while';
IDFOR: 'for';

WRITE : 'write';
READ : 'read';
DEF : 'def';
VAR : 'var';
PROGRAMA : 'programa';
END : 'end';

IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]* ;
NUMERO : [0-9]*;

STRING : STRINGPARTICAO '"';
STRINGPARTICAO : '"' (~["\\\r\n] | '\\' (. | EOF))*;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
