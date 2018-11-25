import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorListener extends BaseErrorListener {
	public static final ErrorListener INSTANCE = new ErrorListener();
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {

		String sourceName = recognizer.getInputStream().getSourceName();
		if (!sourceName.isEmpty()) {
			sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
		}
		Errors newErro = new Errors();
		newErro.setLinha(line);
		newErro.setColuna(charPositionInLine);
		newErro.setTipo("Erro");
		newErro.setMensagem(msg);
		HanglingErrors.addErro(newErro);
		System.err.println(sourceName + "line " + line + ":" + charPositionInLine + " " + msg);
	}

}
