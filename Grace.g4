grammar Grace;

@header {
	import java.util.HashMap;
}

@members {
	
	HashMap memoria = new HashMap();
	int counte = 0;
}

grace: decVar+;

// --------- TODO DECLARACAO DE VARIAVEL ------------ //
decVar :
    VAR
    listaSpecVar
    DOISPONTOS
    tiposPrimitivos
    PONTOVIRGULA
    {
    	System.out.println($listaSpecVar.text);
    }
    ;
      
listaSpecVar :
	specVar
	(VIRGULA specVar)*
	//{System.out.println("nois" + $specVar.value);}
	;     
	   
specVar:
    specVarSimples|
    specVarSimplesIni
    //{
    //	System.out.println("nois" + $specVar.value);
    //}
    //specVarArranjo
    //specVarArranjoIni
    ;

specVarSimples //returns [String iden = $IDENTIFICADOR.text]:
	:IDENTIFICADOR
	//{System.out.println($IDENTIFICADOR.text);} 
    ;

specVarSimplesIni:      
    IDENTIFICADOR
    RECEBE
    decExpressao
    //{System.out.println($IDENTIFICADOR.text);}
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
	;
	
// ----- DECLARACAO DE EXPRESSAO -----
decExpressao: 
	operador valor
	|decExpressao operador decExpressao
	|decExpressao '?' decExpressao ':' decExpressao
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
//	|RESTODIVISAO
	|COMPARA
	|DIFERENTE
	|MAIOR
	|MAIORIGUAL
	|MENOR
	|MENORIGUAL
	|OULOGICO
	|ELOGICO 
	;

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
