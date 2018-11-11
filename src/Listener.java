import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.antlr.v4.parse.ANTLRParser.sync_return;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Listener extends GraceBaseListener {
	
	HashMap memoria = new HashMap();
	List<String> memoria_auxiliar = new ArrayList<>();
	
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
				else
				System.out.println("Variável " + ctx.listaSpecVar().specVar(i).specVarSimples().IDENTIFICADOR() + " já foi declarada.");
			}
			
			if(ctx.listaSpecVar().specVar(i).specVarSimplesIni() != null) {
				
				var.setVariavel(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText());
				
				if(!memoria.containsKey(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText()))
				memoria.put(ctx.listaSpecVar().specVar(i).specVarSimplesIni().IDENTIFICADOR().getText(), var);
			}
		}
		
	}
	
	@Override 
	public void exitDecVar(GraceParser.DecVarContext ctx) {
		/*System.out.println(ctx.getText());
		System.out.println(ctx.getRuleContext());
		System.out.println(ctx.getRuleIndex());
		System.out.println(ctx.toStringTree());
		System.out.println(ctx.getChild(1).getChild(2).getChildCount());
		*/
		//memoria_auxiliar.forEach(dado -> System.out.println(dado.toString()));
		
		/*
		 * descomentar se pá
		 * for(int i = 0; i < memoria_auxiliar.size(); i++) {
			
			EstruturaMemoria var = new EstruturaMemoria();
			var.setVariavel(memoria_auxiliar.get(i));
			var.setTipo(ctx.getChild(ctx.getChildCount()-2).getText());
			
			if(!memoria.containsKey(memoria_auxiliar.get(i))) memoria.put(memoria_auxiliar.get(i), var);
		}
		
		memoria_auxiliar.clear();*/
		
	}
	
	@Override public void exitSpecVarSimples(GraceParser.SpecVarSimplesContext ctx) { 
		
		/* descomentar se pá if(!memoria_auxiliar.contains(ctx.getText())) memoria_auxiliar.add(ctx.getText());*/
		
	}
	
	@Override public void exitSpecVarSimplesIni(GraceParser.SpecVarSimplesIniContext ctx) {
		
		/*if(!memoria_auxiliar.contains(ctx.getChild(0).getText())) memoria_auxiliar.add(ctx.getChild(0).getText())*/;
	}
	
	@Override public void exitValor(GraceParser.ValorContext ctx) { 

		if(!(ctx.IDENTIFICADOR() == null)) {
			if(!memoria.containsKey(ctx.IDENTIFICADOR().getText())) 
				System.out.println("Variável " + ctx.IDENTIFICADOR() + " não declarada.");
		}
			
			
	}
	
	@Override public void exitGrace(GraceParser.GraceContext ctx) {
		
		Set<String> chaves = memoria.keySet();
		
		for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();){
			
			String chave = iterator.next();
			if(chave != null)
				System.out.println(chave + " ----> "+ memoria.get(chave).toString());
		}
	}
}
