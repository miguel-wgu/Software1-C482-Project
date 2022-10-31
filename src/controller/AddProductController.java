package controller;

import helper.ErrMsg;
import helper.ReturnToMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static helper.ErrMsg.*;

/**
 * Controller class for AddProduct.fxml.
 * <p>
 * Provides functionality for the add product screen.
 *
 * @author Miguel Guzman
 */
public class AddProductController implements Initializable {

	ObservableList<Part> associatedParts = FXCollections.observableArrayList();
	@FXML
	private TableView<Part> partsTableView;
	@FXML
	private TableColumn<Part, Integer> partIDCol;
	@FXML
	private TableColumn<Part, String> partNameCol;
	@FXML
	private TableColumn<Part, Integer> partInvLvlCol;
	@FXML
	private TableColumn<Part, Double> partCostCol;
	@FXML
	private TableView<Part> associatedPartsTableView;
	@FXML
	private TableColumn<Part, Integer> associatedPartIdCol;
	@FXML
	private TableColumn<Part, String> associatedPartNameCol;
	@FXML
	private TableColumn<Part, Integer> associatedPartInvLvlCol;
	@FXML
	private TableColumn<Part, Double> associatedPartCostCol;
	@FXML
	private TextField productIdTextField;
	@FXML
	private TextField productNameTextField;
	@FXML
	private TextField productInvTextField;
	@FXML
	private TextField productPriceTextField;
	@FXML
	private TextField productMaxTextField;
	@FXML
	private TextField productMinTextField;

	public void partSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Part> allParts = Inventory.getAllParts();
		ObservableList<Part> searchResults = FXCollections.observableArrayList();
		for (Part part : allParts) {
			if (part.getName().contains(input) || Integer.toString(part.getId()).contains(input))
				searchResults.add(part);
		}
		partsTableView.setItems(searchResults);
		if (searchResults.isEmpty()) {
			ErrMsg.displayErrMsg(10);
			partsTableView.setItems(allParts);
			search.clear();
		}
	}

	public void addToAssociatedPartsOnClick(ActionEvent actionEvent) {
		Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				associatedPartsTableView.getItems().add(selectedPart);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(11);
		}

	}

	public void removeAssociatedPartOnClick(ActionEvent actionEvent) {
		Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				associatedPartsTableView.getItems().remove(selectedPart);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(11);
		}
	}

	@FXML
	void saveProductOnClick(ActionEvent actionEvent) throws IOException {
		int id = Inventory.getAllProducts().size() + 1;
		isValid(productNameTextField, productInvTextField, productPriceTextField, productMaxTextField, productMinTextField);

		try {
			String productName = productNameTextField.getText();
			int productInv = Integer.parseInt(productInvTextField.getText());
			double productPrice = Double.parseDouble(productPriceTextField.getText());
			int productMax = Integer.parseInt(productMaxTextField.getText());
			int productMin = Integer.parseInt(productMinTextField.getText());


			if (!(ErrMsg.verifyMin(productMin, productMax)) || !(ErrMsg.verifyInv(productInv, productMin, productMax))) {
				System.Logger logger = System.getLogger("AddProductController");
				logger.log(System.Logger.Level.ERROR, "Invalid input");
			} else {
				Product product = new Product(id, productName, productPrice, productInv, productMin, productMax);
				for (Part part : associatedParts) {
					product.addAssociatedPart(part);
				}
				Inventory.addProduct(product);
				ReturnToMainWindow.mainWindow(actionEvent);
				System.out.println("product added");

			}
		} catch (NumberFormatException e) {
			System.Logger logger = System.getLogger("AddProductController");
			logger.log(System.Logger.Level.ERROR, "Error: " + e.getMessage());
		}
	}

	@FXML
	void cancelProductOnClick(ActionEvent actionEvent) throws IOException {
		ReturnToMainWindow.mainWindow(actionEvent);
	}

	/**
	 * Initialize.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		partsTableView.setItems(Inventory.getAllParts());
		partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		partInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		associatedPartsTableView.setItems(associatedParts);
		associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		associatedPartInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		associatedPartCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}


}

