package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	public void exitOnClick(ActionEvent actionEvent) {
		System.exit(0);
	}
}