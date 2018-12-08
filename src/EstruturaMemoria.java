import java.util.List;

public class EstruturaMemoria {

	
	private String cadeia;
	private String tipo;
	private String categoria;
	private String endereco;
	private List<Bana> parametros;
	private String valor;
	
	public String getCadeia() {
		return cadeia;
	}
	public void setCadeia(String cadeia) {
		this.cadeia = cadeia;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}	
	public List<Bana> getParametros() {
		return parametros;
	}
	public void setParametros(List<Bana> parametros) {
		this.parametros = parametros;
	}
	
	@Override
	public String toString() {
		return "EstruturaMemoria [variavel=" + cadeia + ", tipo=" + tipo + ", valor=" + valor + "]";
	}
	
	
}
