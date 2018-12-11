import java.util.ArrayList;
import java.util.List;

public class Listener extends GraceBaseListener {
	
	Simbolo tabelaSimbolos = new Simbolo();
	List<String> verificaTipo = new ArrayList<>();	
	List<Argumentos> parametrosAtivos = new ArrayList<>();
	
	@Override 
	public void enterDecVar(GraceParser.DecVarContext ctx) {
		
		for(int i = 0; i< ctx.listaSpecVar().specVar().size();i++) {
			EstruturaMemoria var = new EstruturaMemoria();
			var.setTipo(ctx.getChild(ctx.getChildCount()-2).getText());
			var.setCategoria("var");

			if(ctx.listaSpecVar().specVar(i).specVarSimples() != null) {
				
				var.setCadeia(ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR().getText());
				
				if(!tabelaSimbolos.contains(ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR().getText()))
					tabelaSimbolos.addSimbolo(ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR().getText(), var);
				else {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Variável '" + ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR() +
							"' já foi declarada.");
					HanglingErrors.addErro(newErro);
					System.out.println("Variável '" + ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR() + "' já foi declarada.");
				}
			}
			
			if(ctx.listaSpecVar().specVar(i).specVarSimplesIni() != null) {
	
				var.setCadeia(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText());
				
				if(!tabelaSimbolos.contains(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText()))
					tabelaSimbolos.addSimbolo(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText(), var);
				else {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Variável '" + ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR() +
							" já foi declarada.");
					HanglingErrors.addErro(newErro);
					System.out.println("Variável '" + ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR() + " já foi declarada.");
				}
			}
		}
	}
		
	@Override 
	public void exitSpecVarSimplesIni(GraceParser.SpecVarSimplesIniContext ctx) {
		
		//verificaTipo.forEach(dado-> System.out.println(dado));
		
		if(verificaTipo.size() > 1) {
			String tipoPadrao = verificaTipo.get(0);
			for(String tipo : verificaTipo) {
				if(!tipo.equals(tipoPadrao)) {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Conversão inválida de " + tipo + " para " + tipoPadrao);
					HanglingErrors.addErro(newErro);
					System.out.println("Conversão inválida de " + tipo + " para " + tipoPadrao);
				}
			}
		}
		
		if(!verificaTipo.isEmpty()) {
			if(!(tabelaSimbolos.getSimbolo(ctx.getChild(0).getText()).getTipo().equals(verificaTipo.get(0).toString()))) {
				Errors newErro = new Errors();
				newErro.setTipo("Erro");
				newErro.setLinha(ctx.getStop().getLine());
				newErro.setColuna(ctx.getStop().getCharPositionInLine());
				newErro.setMensagem("Atribuição inválida de " + tabelaSimbolos.getSimbolo(ctx.getChild(0).getText()).getTipo() + " para " + verificaTipo.get(0));
				HanglingErrors.addErro(newErro);
				
				System.out.println("Atribuição inválida de " + tabelaSimbolos.getSimbolo(ctx.getChild(0).getText()).getTipo() + " para " + verificaTipo.get(0));
				verificaTipo.clear();
			}
		}
	}
	
	@Override 
	public void exitValor(GraceParser.ValorContext ctx) { 
		if(!(ctx.IDENTIFICADOR() == null)) {
			if(!tabelaSimbolos.contains(ctx.IDENTIFICADOR().getText())) {
				Errors newErro = new Errors();
				newErro.setTipo("Erro");
				newErro.setLinha(ctx.getStop().getLine());
				newErro.setColuna(ctx.getStop().getCharPositionInLine());
				newErro.setMensagem("Variável '" + ctx.IDENTIFICADOR() + "' não declarada.");
				HanglingErrors.addErro(newErro);
				System.out.println("Variável '" + ctx.IDENTIFICADOR() + "' não declarada.");
			}
		}
			
	}

	@Override 
	public void exitValorRelacional(GraceParser.ValorRelacionalContext ctx) { 

		if(!(ctx.IDENTIFICADOR() == null)) {
			if(!tabelaSimbolos.contains(ctx.IDENTIFICADOR().getText())) {
				Errors newErro = new Errors();
				newErro.setTipo("Erro");
				newErro.setLinha(ctx.getStop().getLine());
				newErro.setColuna(ctx.getStop().getCharPositionInLine());
				newErro.setMensagem("Variável '" + ctx.IDENTIFICADOR() + "' não declarada.");
				HanglingErrors.addErro(newErro);
				System.out.println("Variável '" + ctx.IDENTIFICADOR() + "' não declarada.");
			}
		}
			
	}

	@Override 
	public void enterDecExpressao(GraceParser.DecExpressaoContext ctx) {

		if(!(ctx.valor() == null)){
			
			if(ctx.valor().IDENTIFICADOR() != null){
				if(tabelaSimbolos.contains(ctx.valor().getText())) 
					verificaTipo.add(tabelaSimbolos.getSimbolo(ctx.valor().getText()).getTipo());	
			}
			
			else if(ctx.valor().NUMERO() != null){
					verificaTipo.add("int");	
			}
			
			else if(ctx.valor().STRING() != null){
				verificaTipo.add("string");	
			}
			
			else if(ctx.valor().BOOLEAN() != null){
				verificaTipo.add("bool");	
			}
		}
		
	}
	
	@Override 
	public void enterDecExpressaoRelacional(GraceParser.DecExpressaoRelacionalContext ctx) { 
		
		if(!(ctx.valorRelacional() == null)){
			
			if(ctx.valorRelacional().IDENTIFICADOR() != null){
				if(tabelaSimbolos.contains(ctx.valorRelacional().getText())) {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Variável '" + ctx.valorRelacional().IDENTIFICADOR() + "' não declarada.");
				}	
			}
		}
	}
	

	@Override 
	public void exitGrace(GraceParser.GraceContext ctx) {
		/*
		Set<String> chaves = memoria.keySet();
		
		for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();){
			
			String chave = iterator.next();
			if(chave != null)
			System.out.println(chave + " ----> "+ memoria.getEstrutura(chave).toString());
		}*/
	}
	
	@Override public void enterBloco(GraceParser.BlocoContext ctx) { 
		tabelaSimbolos.addEscopo();
		
		if(!parametrosAtivos.isEmpty()) {
			for(Argumentos dado : parametrosAtivos) {
				EstruturaMemoria nova = new EstruturaMemoria();
				nova.setCadeia(dado.getNome());
				nova.setTipo(dado.getTipo());
				nova.setCategoria("var");
				tabelaSimbolos.addSimbolo(dado.getNome(), nova);
			}
			parametrosAtivos.clear();
		}
	}

	@Override public void exitBloco(GraceParser.BlocoContext ctx) { 
		tabelaSimbolos.removeEscopo();
	}
	
	@Override public void enterDecFunc(GraceParser.DecFuncContext ctx) {
		System.out.println(ctx);
	}
	
	@Override public void enterDecProc(GraceParser.DecProcContext ctx) { 

		if(tabelaSimbolos.contains(ctx.getChild(1).getText())) {
			Errors newErro = new Errors();
			newErro.setTipo("Erro");
			newErro.setLinha(ctx.getStart().getLine());
			newErro.setColuna(ctx.getStart().getCharPositionInLine() + ctx.getChild(0).getText().length());
			newErro.setMensagem("Procedimento '" + ctx.getChild(1).getText() +
					" já foi declarada.");
			HanglingErrors.addErro(newErro);
			System.out.println("Procedimento '" + ctx.getChild(1).getText() + " já foi declarada.");
		}
		
		EstruturaMemoria var = new EstruturaMemoria();
		var.setCadeia(ctx.getChild(1).getText());
		List<Argumentos> listArgumentos = new ArrayList<>();
		for(GraceParser.SpecParamsContext dado : ctx.listaParametros().specParams()) {
			Argumentos t = new Argumentos();
			t.setNome(dado.param(0).getText());
			t.setTipo(dado.tiposPrimitivos().getText());
			listArgumentos.add(t);
			parametrosAtivos.add(t);
		}
		var.setParametros(listArgumentos);
		var.setCategoria("proc");
		var.setTipo("void");
		tabelaSimbolos.addSimbolo(ctx.getChild(1).getText(), var);
	}
	
	@Override public void enterCmdAtrib(GraceParser.CmdAtribContext ctx) {
		if(!tabelaSimbolos.contains(ctx.atrib().IDENTIFICADOR().getText())) {
			Errors newErro = new Errors();
			newErro.setTipo("Erro");
			newErro.setLinha(ctx.getStop().getLine());
			newErro.setColuna(ctx.getStop().getCharPositionInLine());
			newErro.setMensagem("Variável '" + ctx.atrib().IDENTIFICADOR() + "' não declarada.");
			HanglingErrors.addErro(newErro);
			System.out.println("Variável '" + ctx.atrib().IDENTIFICADOR() + "' não declarada.");
		}
	}
	
}
