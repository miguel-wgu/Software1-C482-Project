package controller;

import helper.CommonFunctions;
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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static helper.ErrMsg.isValid;

public class ModifyProductController implements Initializable {

	private Product selectedProduct;
	private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


	@FXML
	private TableView<Part> partsTableView;
	@FXML
	private TableView<Part> associatedPartsTableView;
	@FXML
	private TableColumn<Part, Integer> partIDCol;
	@FXML
	private TableColumn<Part, String> partNameCol;
	@FXML
	private TableColumn<Part, Integer> partInvLvlCol;
	@FXML
	private TableColumn<Part, Double> partCostCol;
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


	@FXML
	void saveProductOnClick(ActionEvent actionEvent) {
		int id = selectedProduct.getId();
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
				Inventory.deleteProduct(selectedProduct);
				ReturnToMainWindow.mainWindow(actionEvent);
				System.out.println("product added");

			}
		} catch (NumberFormatException | IOException e) {
			System.Logger logger = System.getLogger("AddProductController");
			logger.log(System.Logger.Level.ERROR, "Error: " + e.getMessage());
		}
	}

	public void cancelProductOnClick(ActionEvent actionEvent) throws IOException {
		ReturnToMainWindow.mainWindow(actionEvent);
	}

	public void modPartSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Part> allParts = Inventory.getAllParts();
		CommonFunctions.getSearchResults(search, input, allParts, partsTableView);
	}

	public void addToAssociatedPartsOnClick() {
		Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				associatedPartsTableView.getItems().add(selectedPart);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(11);
		}
	}

	public void removeAssociatedPartOnClick() {
		Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				associatedPartsTableView.getItems().remove(selectedPart);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(14);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedProduct = MainController.getSelectedProduct();
		associatedParts = selectedProduct.getAllAssociatedParts();
		productIdTextField.setText(String.valueOf(selectedProduct.getId()));
		productNameTextField.setText(selectedProduct.getName());
		productInvTextField.setText(String.valueOf(selectedProduct.getStock()));
		productPriceTextField.setText(String.valueOf(selectedProduct.getPrice()));
		productMaxTextField.setText(String.valueOf(selectedProduct.getMax()));
		productMinTextField.setText(String.valueOf(selectedProduct.getMin()));
		CommonFunctions.getPartsTable(partsTableView, partIDCol, partNameCol, partInvLvlCol, partCostCol, associatedPartsTableView, associatedParts, associatedPartIdCol, associatedPartNameCol, associatedPartInvLvlCol, associatedPartCostCol);
	}
}
