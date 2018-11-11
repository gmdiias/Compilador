
public class EstruturaMemoria {

	private String variavel;
	private String tipo;
	private String valor;
	
	public String getVariavel() {
		return variavel;
	}
	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "EstruturaMemoria [variavel=" + variavel + ", tipo=" + tipo + ", valor=" + valor + "]";
	}
	
	
}
