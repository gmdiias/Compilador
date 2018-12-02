import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JUnitTests {

	@Test
	void DecVarVariavelJaDeclaradaTest() {
		GraceRunner grace = new GraceRunner();
		StringBuilder codigo = new StringBuilder();
		codigo.append("var c: int;");
		codigo.append("var c = a : bool;");
		String result = grace.compilar(codigo.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("<Erro> 1:27 - Variável 'c já foi declarada.");
		expected.append(System.lineSeparator());
		expected.append("<Erro> 1:19 - Variável 'a' não declarada.");
		expected.append(System.lineSeparator());
		
		assertEquals(expected.toString(), result);
	}
	
	@Test
	void DecVarVariavelNaoDeclaradaTest() {
		GraceRunner grace = new GraceRunner();
		StringBuilder codigo = new StringBuilder();
		codigo.append("var c = a: bool;");
		String result = grace.compilar(codigo.toString());
		
		assertEquals("<Erro> 1:8 - Variável 'a' não declarada." + System.lineSeparator(), result);
	}
	
	@Test
	void EscopoVarTest() {
		GraceRunner grace = new GraceRunner();
		StringBuilder codigo = new StringBuilder();
		codigo.append("var a = 1 : int;");
		codigo.append("if(a) {");
		codigo.append("	var b = 2 : int;");
		codigo.append("}");
		codigo.append("else {");
		codigo.append("	var c = 2 : int;");
		codigo.append("}");
		codigo.append("var c = 3 : int;");
		codigo.append("var a = 1 : int;");
		String result = grace.compilar(codigo.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("<Erro> 1:27 - Variável 'a já foi declarada.");
		expected.append(System.lineSeparator());
		
		assertEquals(expected.toString(), result);
	}

}
