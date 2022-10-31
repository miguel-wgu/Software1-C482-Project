package controller;

import helper.CommonFunctions;
import helper.ErrMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static helper.ErrMsg.isValid;


/********RUNTIME ERROR********
 * After selecting a product, clicking modify would throw an error.
 * It stated that the selectedProduct was null and contained no information.
 * I solved this by going back to the MainController making small changes to see
 * anything different would happen. It turns out that selectedProduct was not initialized
 * correctly which explains why the app worked, but would not load the selected product.
 *
 * Controller class for AddProduct.fxml.
 *
 * Provides functionality for the add product screen.
 *
 * @author Miguel Guzman
 */
public class ModifyProductController implements Initializable {
	/**
	 * The Selected product.
	 */
	private Product selectedProduct;
	/**
	 * The Associated parts list.
	 */
	private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
	/**
	 * The Parts table view.
	 */
	@FXML
	private TableView<Part> partsTableView;
	/**
	 * The Associated parts table view.
	 */
	@FXML
	private TableView<Part> associatedPartsTableView;
	/**
	 * The Part id col.
	 */
	@FXML
	private TableColumn<Part, Integer> partIDCol;
	/**
	 * The Part name col.
	 */
	@FXML
	private TableColumn<Part, String> partNameCol;
	/**
	 * The Part inv lvl col.
	 */
	@FXML
	private TableColumn<Part, Integer> partInvLvlCol;
	/**
	 * The Part cost col.
	 */
	@FXML
	private TableColumn<Part, Double> partCostCol;
	/**
	 * The Associated part id col.
	 */
	@FXML
	private TableColumn<Part, Integer> associatedPartIdCol;
	/**
	 * The Associated part name col.
	 */
	@FXML
	private TableColumn<Part, String> associatedPartNameCol;
	/**
	 * The Associated part inv lvl col.
	 */
	@FXML
	private TableColumn<Part, Integer> associatedPartInvLvlCol;
	/**
	 * The Associated part cost col.
	 */
	@FXML
	private TableColumn<Part, Double> associatedPartCostCol;
	/**
	 * The Product id text field.
	 */
	@FXML
	private TextField productIdTextField;
	/**
	 * The Product name text field.
	 */
	@FXML
	private TextField productNameTextField;
	/**
	 * The Product inv text field.
	 */
	@FXML
	private TextField productInvTextField;
	/**
	 * The Product price text field.
	 */
	@FXML
	private TextField productPriceTextField;
	/**
	 * The Product max text field.
	 */
	@FXML
	private TextField productMaxTextField;
	/**
	 * The Product min text field.
	 */
	@FXML
	private TextField productMinTextField;

	/**
	 * Save product if validation checks are passed.
	 * Included appropriate error messages. Includes
	 * logger to log errors just to get some practice
	 * with them.
	 *
	 * @param actionEvent the action event.
	 */
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
				CommonFunctions.mainWindow(actionEvent);
			}
		} catch (NumberFormatException | IOException e) {
			System.Logger logger = System.getLogger("AddProductController");
			logger.log(System.Logger.Level.ERROR, "Error: " + e.getMessage());
		}
	}

	/**
	 * Cancel product on click and return to main window.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	public void cancelProductOnClick(ActionEvent actionEvent) throws IOException {
		CommonFunctions.mainWindow(actionEvent);
	}

	/**
	 * Search and returns parts that match the search criteria.
	 *
	 * @param actionEvent the action event
	 */
	public void modPartSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Part> allParts = Inventory.getAllParts();
		CommonFunctions.getSearchResults(search, input, allParts, partsTableView);
	}

	/**
	 * Add to associated parts list on click.
	 */
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

	/**
	 * Remove associated part from table on click.
	 */
	public void removeAssociatedPartOnClick() {
		Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				Alert confirm = ErrMsg.createAlert();
				confirm.setContentText("Are you sure you want to remove the associated part?");
				Optional<ButtonType> input = confirm.showAndWait();
				if (input.isPresent() && input.get() == ButtonType.OK) {
					associatedPartsTableView.getItems().remove(selectedPart);
				}
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(14);
		}
	}

	/**
	 * Initialize product data and table views.
	 *
	 * @param url            the url.
	 * @param resourceBundle the resource bundle.
	 */
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
		CommonFunctions.getPartsTable(partsTableView, partIDCol, partNameCol,
				partInvLvlCol, partCostCol, associatedPartsTableView,
				associatedParts, associatedPartIdCol, associatedPartNameCol,
				associatedPartInvLvlCol, associatedPartCostCol);
	}
}