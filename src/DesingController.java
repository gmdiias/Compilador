
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DesingController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane janela;
	
	@FXML
	private Button btnCompilar;
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private TextField textField;
	
	@FXML
	private void onCompilarClick() {
		System.out.println("Iniciando compilacao do sistema ...");
		System.out.println(textArea.getText());
		GraceRunner graceRunner = new GraceRunner();
		
		textField.setText(graceRunner.compilar(textArea.getText()));
	}

	
}
