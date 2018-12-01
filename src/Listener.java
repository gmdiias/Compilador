import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Listener extends GraceBaseListener {
	
	HashMap<String, EstruturaMemoria> memoria = new HashMap<>();
	List<String> verificaTipo = new ArrayList<>();	
	
	List<EstruturaMemoria> estruturaMemoria = new ArrayList<>();
	
	@Override 
	public void enterDecVar(GraceParser.DecVarContext ctx) {
		
		for(int i = 0; i< ctx.listaSpecVar().specVar().size();i++) {
			EstruturaMemoria var = new EstruturaMemoria();
			var.setTipo(ctx.getChild(ctx.getChildCount()-2).getText());

			if(ctx.listaSpecVar().specVar(i).specVarSimples() != null) {
				
				var.setVariavel(ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR().getText());
				
				if(!memoria.containsKey(ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR().getText()))
					memoria.put(ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR().getText(), var);
				else {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Vari�vel '" + ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR() +
							"' j� foi declarada.");
					HanglingErrors.addErro(newErro);
					System.out.println("Vari�vel '" + ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR() + "' j� foi declarada.");
				}
			}
			
			if(ctx.listaSpecVar().specVar(i).specVarSimplesIni() != null) {
	
				var.setVariavel(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText());
				
				if(!memoria.containsKey(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText()))
					memoria.put(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText(), var);
				else {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Vari�vel '" + ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR() +
							" j� foi declarada.");
					HanglingErrors.addErro(newErro);
					System.out.println("Vari�vel '" + ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR() + " j� foi declarada.");
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
					newErro.setMensagem("Convers�o inv�lida de " + tipo + " para " + tipoPadrao);
					HanglingErrors.addErro(newErro);
					System.out.println("Convers�o inv�lida de " + tipo + " para " + tipoPadrao);
				}
			}
		}
		
		if(!verificaTipo.isEmpty()) {
			if(!(memoria.get(ctx.getChild(0).getText()).getTipo().equals(verificaTipo.get(0).toString()))) {
				Errors newErro = new Errors();
				newErro.setTipo("Erro");
				newErro.setLinha(ctx.getStop().getLine());
				newErro.setColuna(ctx.getStop().getCharPositionInLine());
				newErro.setMensagem("Atribui��o inv�lida de " + memoria.get(ctx.getChild(0).getText()).getTipo() + " para " + verificaTipo.get(0));
				HanglingErrors.addErro(newErro);
				
				verificaTipo.clear();
				System.out.println("Atribui��o inv�lida de " + memoria.get(ctx.getChild(0).getText()).getTipo() + " para " + verificaTipo.get(0));
			}
		}
	}
	
	@Override 
	public void exitValor(GraceParser.ValorContext ctx) { 
		if(!(ctx.IDENTIFICADOR() == null)) {
			if(!memoria.containsKey(ctx.IDENTIFICADOR().getText())) {
				Errors newErro = new Errors();
				newErro.setTipo("Erro");
				newErro.setLinha(ctx.getStop().getLine());
				newErro.setColuna(ctx.getStop().getCharPositionInLine());
				newErro.setMensagem("Vari�vel '" + ctx.IDENTIFICADOR() + "' n�o declarada.");
				HanglingErrors.addErro(newErro);
				System.out.println("Vari�vel '" + ctx.IDENTIFICADOR() + "' n�o declarada.");
			}
		}
			
	}

	@Override 
	public void exitValorRelacional(GraceParser.ValorRelacionalContext ctx) { 

		if(!(ctx.IDENTIFICADOR() == null)) {
			if(!memoria.containsKey(ctx.IDENTIFICADOR().getText())) {
				Errors newErro = new Errors();
				newErro.setTipo("Erro");
				newErro.setLinha(ctx.getStop().getLine());
				newErro.setColuna(ctx.getStop().getCharPositionInLine());
				newErro.setMensagem("Vari�vel '" + ctx.IDENTIFICADOR() + "' n�o declarada.");
				HanglingErrors.addErro(newErro);
				System.out.println("Vari�vel '" + ctx.IDENTIFICADOR() + "' n�o declarada.");
			}
		}
			
	}

	@Override 
	public void enterDecExpressao(GraceParser.DecExpressaoContext ctx) {

		if(!(ctx.valor() == null)){
			
			if(ctx.valor().IDENTIFICADOR() != null){
				if(memoria.containsKey(ctx.valor().getText())) 
					verificaTipo.add(memoria.get(ctx.valor().getText()).getTipo());	
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
				if(memoria.containsKey(ctx.valorRelacional().getText())) {
					Errors newErro = new Errors();
					newErro.setTipo("Erro");
					newErro.setLinha(ctx.getStop().getLine());
					newErro.setColuna(ctx.getStop().getCharPositionInLine());
					newErro.setMensagem("Vari�vel '" + ctx.valorRelacional().IDENTIFICADOR() + "' n�o declarada.");
				}	
			}
		}
	}
	

	@Override 
	public void exitGrace(GraceParser.GraceContext ctx) {
		
		Set<String> chaves = memoria.keySet();
		
		for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();){
			
			String chave = iterator.next();
			//if(chave != null)
			//	System.out.println(chave + " ----> "+ memoria.get(chave).toString());
		}
	}
}
