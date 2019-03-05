package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class WelcomeWindowController implements Initializable {

	@FXML
	private AnchorPane stageOne;

	@FXML
	private Button stageOneEnterButton;

	@FXML
	private Button stageOneExitButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void stageOneEnter(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("SecondWindow.fxml"));
		stageOne.getChildren().setAll(pane);
	}

	@FXML
	private void stageOneExit() {
		System.exit(0);

	}

}
