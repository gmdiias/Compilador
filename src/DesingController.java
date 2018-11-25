
import java.io.Serializable;

import javax.swing.text.Position;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class DesingController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane janela;
	
	@FXML
	private Button btnCompilar;
	
	@FXML
	private TextArea userCodeArea;
	
	@FXML
	private TextArea consoleArea;
	
	@FXML
	private void onCompilarClick() {
		System.out.println("Iniciando compilacao do sistema ...");
		System.out.println(userCodeArea.getText());
		GraceRunner graceRunner = new GraceRunner();
		consoleArea.setText(graceRunner.compilar(userCodeArea.getText()));
		
		
		//int linha = userCodeArea.getText().split("\n").length;
		//int coluna = userCodeArea.getCaretPosition();
		//System.out.println("Linha: "+ linha + " Coluna: "+coluna);
	}
	
}
