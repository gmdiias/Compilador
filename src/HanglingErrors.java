import java.util.ArrayList;
import java.util.List;

public class HanglingErrors {
	private static List<Errors> listErros = new ArrayList<>();

	public static List<Errors> getListErros() {
		return listErros;
	}

	public static void setListErros(List<Errors> listErros) {
		HanglingErrors.listErros = listErros;
	}
	
	public static void resetListErros() {
		listErros.clear();
	}
	
	public static void addErro(Errors erro) {
		HanglingErrors.listErros.add(erro);
	}
	
	public static String mostrarErro() {
		StringBuilder errosString = new StringBuilder();
		
		HanglingErrors.getListErros().forEach(dado -> {
			errosString.append("<");
			errosString.append(dado.getTipo());
			errosString.append("> ");
			errosString.append(dado.getLinha());
			errosString.append(":");
			errosString.append(dado.getColuna());
			errosString.append(" - ");
			errosString.append(dado.getMensagem());
			errosString.append(System.lineSeparator());
		});
		
		return errosString.toString();
	}
}
