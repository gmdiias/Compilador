import java.util.HashMap;

public class ListMemoria {
	private HashMap<String, EstruturaMemoria> memoria = new HashMap<>();
	private ListMemoria proximo;
	
	public void addContexto() {
		if(proximo == null) {
			proximo = new ListMemoria();
			return;
		}
		proximo.addContexto();
	}
	
	public void removeContexto() {
		if(proximo.proximo == null) {
			proximo = null;
			return;
		}
		
		proximo.removeContexto();
	}
	
	public boolean contains(String key) {
		if(memoria.containsKey(key))
			return true;
		if(proximo != null)
			return proximo.contains(key);
		
		return false;
	}
	
	public void addEstrutura(String key, EstruturaMemoria estruturaMemoria) {
		if(proximo == null) {
			memoria.put(key, estruturaMemoria);
			return;
		}
		
		proximo.addEstrutura(key, estruturaMemoria);
	}
	
	public EstruturaMemoria getEstrutura(String key) {
		if(memoria.containsKey(key))
			return memoria.get(key);
		if(proximo != null)
			return proximo.getEstrutura(key);
		
		return null;
	}
}
