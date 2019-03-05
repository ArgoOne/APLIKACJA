package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ExitWindowController implements Initializable {

	@FXML
	private AnchorPane stageThree;

	@FXML
	private Button exitButton;

	@FXML
	public void exitThree() {

		Alert alert = new Alert(AlertType.CONFIRMATION, "CZY NAPEWNO CHCESZ WYJSC Z PROGRAMU?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK)

			System.exit(0);

		if (result.isPresent() && result.get() == ButtonType.CANCEL)
			;

	}

	@FXML
	private void exitTwo(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
		stageThree.getChildren().setAll(pane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
