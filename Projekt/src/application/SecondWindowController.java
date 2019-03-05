package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class SecondWindowController implements Initializable {

	@FXML
	private AnchorPane stageTwo;

	@FXML
	private Button entranceButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button addButton;
	@FXML
	private Button pageNextThreeButton;
	@FXML
	private Button exitTwoButton;
	@FXML
	private Button filtrButton;
	@FXML
	private Button filtrButton1;
	@FXML
	private Button clearButton;

	@FXML
	private Label LabelWord;
	@FXML
	private Label LabelDescribe;
	@FXML
	private Label LabelLiczba;

	@FXML
	private TextField TextFieldWord;
	@FXML
	private TextField TextFieldDescribe;
	@FXML
	private TextField TextFieldIlosc;
	@FXML
	private TextField TextFieldLitera;

	@FXML
	private CheckBox opisbox;
	@FXML
	private TableView<Word> tableView;
	@FXML
	private TableColumn<Word, String> wordColumn;
	@FXML
	private TableColumn<Word, String> describeColumn;

	private ObservableList<Word> wordList = FXCollections.observableArrayList();

	private ObservableList<Word> listaliter = FXCollections.observableArrayList();

	private ObservableList<Word> listaliter1 = FXCollections.observableArrayList();

	String word;
	String describe;
	Integer liczbaznakow;

	Scanner in = null;
	PrintWriter out = null;
	PrintWriter raport = null;

	private File selectedFile;
	public File file;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word"));
		describeColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("describe"));

		tableView.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {

			TextFieldWord.setText(newVal.getWord());
			TextFieldDescribe.setText(newVal.getDescribe());

		});
	}

	// wczytanie pliku
	@FXML
	public void entranceFile() {

		FileChooser fc = new FileChooser();
		fc.setTitle("WYBIERZ PLIK");

		FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fc.getExtensionFilters().add(fileFilter);

		File selectedFile = fc.showOpenDialog(null);
		this.selectedFile = selectedFile;

		tableView.setItems(wordList);

		if (selectedFile != null) {

			try {

				in = new Scanner(Paths.get(selectedFile.toURI()));

				in.useDelimiter(";"); // wlasny separator

				while (in.hasNext()) {

					word = in.next();
					describe = in.next();
					liczbaznakow = in.nextInt();

					wordList.add(new Word(word, describe, liczbaznakow));

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				if (in != null)
					in.close();
			}
		}
	}

	// dodawanie slowa i nadpisywanie pliku
	@FXML
	public void addWord() {

		wordList.add(new Word(TextFieldWord.getText(), TextFieldDescribe.getText(), liczbaznakow));

		try {

			out = new PrintWriter(selectedFile);

			for (int i = 0; i < wordList.size(); i++) {

				out.printf("%s;%s;%d;",

						wordList.get(i).getWord(), wordList.get(i).getDescribe(), wordList.get(i).getLiczbaznakow());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	@FXML
	public void saveFile() {

		FileChooser fc = new FileChooser();
		fc.setTitle("ZAPISZ PLIK");
		FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fc.getExtensionFilters().add(fileFilter);
		File file = fc.showSaveDialog(null);

		try {

			raport = new PrintWriter(file);

			for (int i = 0; i < listaliter1.size(); i++) {

				raport.printf("%s;%s;%d;",

						listaliter1.get(i).getWord(), listaliter1.get(i).getDescribe(),
						listaliter1.get(i).getLiczbaznakow());

			}

		} catch (IOException e) {

			Alert alert = new Alert(AlertType.INFORMATION, "wybierz plik");
			Optional<ButtonType> result = alert.showAndWait();

		} finally {

			if (raport != null)
				raport.close();
		}
	}

	// filtrowanie ilosc liter
	@FXML
	public void filtrFile() {

		try {

			Word word;

			Integer n = Integer.parseInt(TextFieldIlosc.getText());

			for (int i = 0; i < wordList.size(); i++) {

				word = wordList.get(i);

				if (word.getWord().length() == n) {

					listaliter.add(word);

					tableView.setItems(listaliter);

					wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word"));

					describeColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("describe"));
				}
			}
		} catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.INFORMATION, "wprowadz cyfre");
			Optional<ButtonType> result = alert.showAndWait();
		}
	}

	// filtrowanie od ktorej litery zaczyna sie wyraz
	@FXML
	public void filtrFile1() {

		try {

			Word word;

			String s = TextFieldLitera.getText().toUpperCase();

			for (int i = 0; i < listaliter.size(); i++) {

				word = listaliter.get(i);

				if (opisbox == null && word.getWord().toUpperCase().startsWith(s)) {

					listaliter1.add(word);

					tableView.setItems(listaliter1);

					wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word"));

					describeColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("describe"));

				}

				else if (opisbox != null && word.getDescribe().toUpperCase().startsWith(s)) {

					listaliter1.add(word);

					tableView.setItems(listaliter1);

					wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word"));

					describeColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("describe"));

				}

			}
		} catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.INFORMATION, "wprowadz litere");
			Optional<ButtonType> result = alert.showAndWait();

		}
	}

	@FXML
	public void clearFile() {

		wordList.clear();
		listaliter.clear();
		listaliter1.clear();
		TextFieldWord.clear();
		TextFieldDescribe.clear();
		TextFieldIlosc.clear();
		TextFieldLitera.clear();

	}

	@FXML
	private void pageNextThree(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("ExitWindow.fxml"));
		stageTwo.getChildren().setAll(pane);
	}

	@FXML
	private void exitTwo() {
		System.exit(0);

	}
}
