package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Part;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

	public Button partAddBtn;
	public Button partModifyBtn;
	public Button partDeleteBtn;
	public TableView partsTableView;
	public TableColumn partIDCol;
	public TableColumn partNameCol;
	public TableColumn partInvLvlCol;
	public TableColumn partCostCol;
	public Button productAddBtn;
	public Button productModifyBtn;
	public Button productDeleteBtn;
	public TableView productsTableView;
	public TableColumn productIDCol;
	public TableColumn productNameCol;
	public TableColumn productInvLvlCol;
	public TableColumn productCostCol;
	public Button exitBtn;

	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	public void exitOnClick() {
		System.exit(0);
	}
}