import java.util.HashMap;

public class Simbolo {
	private HashMap<String, EstruturaMemoria> tabelaSimbolos = new HashMap<>();
	private Simbolo nextEscopo;
	
	public void addEscopo() {
		if(nextEscopo == null) {
			nextEscopo = new Simbolo();
			return;
		}
		nextEscopo.addEscopo();
	}
	
	public void removeEscopo() {
		if(nextEscopo.nextEscopo == null) {
			nextEscopo = null;
			return;
		}
		
		nextEscopo.removeEscopo();
	}
	
	public boolean contains(String key) {
		if(tabelaSimbolos.containsKey(key))
			return true;
		if(nextEscopo != null)
			return nextEscopo.contains(key);
		
		return false;
	}
	
	public void addSimbolo(String key, EstruturaMemoria estruturaMemoria) {
		if(nextEscopo == null) {
			tabelaSimbolos.put(key, estruturaMemoria);
			return;
		}
		
		nextEscopo.addSimbolo(key, estruturaMemoria);
	}
	
	public EstruturaMemoria getSimbolo(String key) {
		if(tabelaSimbolos.containsKey(key))
			return tabelaSimbolos.get(key);
		if(nextEscopo != null)
			return nextEscopo.getSimbolo(key);
		
		return null;
	}
}
