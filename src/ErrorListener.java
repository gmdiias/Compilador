import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorListener extends BaseErrorListener {
	public static final ErrorListener INSTANCE = new ErrorListener();
	
	public static StringBuffer errors = new StringBuffer();

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {

		String sourceName = recognizer.getInputStream().getSourceName();
		if (!sourceName.isEmpty()) {
			sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
		}

		errors.append(sourceName + "line " + line + ":" + charPositionInLine + " " + msg + System.lineSeparator());
		System.err.println(sourceName + "line " + line + ":" + charPositionInLine + " " + msg);
	}

}
