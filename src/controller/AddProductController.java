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

/**
 * Controller class for AddProduct.fxml.
 * <p>
 * Provides functionality for the add product screen.
 *
 * @author Miguel Guzman
 */
public class AddProductController implements Initializable {
	/**
	 * The Associated Parts list used to store parts associated with a product.
	 */
	ObservableList<Part> associatedParts = FXCollections.observableArrayList();
	/**
	 * The Parts table view.
	 */
	@FXML
	private TableView<Part> partsTableView;
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
	 * The Associated parts table view.
	 */
	@FXML
	private TableView<Part> associatedPartsTableView;
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
	 * Searches for product by name or ID.
	 * <p>
	 * If no product is found, an error message will be displayed,
	 * the search field will clear, and the table will be refreshed.
	 *
	 * @param actionEvent defines the action of the search field.
	 */
	public void partSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Part> allParts = Inventory.getAllParts();
		CommonFunctions.getSearchResults(search, input, allParts, partsTableView);
	}

	/**
	 * Add to associated parts table and list on click.
	 *
	 * @param actionEvent the action event.
	 */
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

	/**
	 * Remove associated part on click.
	 *
	 * @param actionEvent the action event.
	 */
	public void removeAssociatedPartOnClick(ActionEvent actionEvent) {
		Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				associatedPartsTableView.getItems().remove(selectedPart);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(14);
		}
	}

	/**
	 * Save product if validation checks are passed.
	 * Included appropriate error messages. Includes
	 * logger to log errors just to get some practice
	 * with them.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
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
			}
		} catch (NumberFormatException e) {
			System.Logger logger = System.getLogger("AddProductController");
			logger.log(System.Logger.Level.ERROR, "Error: " + e.getMessage());
		}
	}

	/**
	 * Cancel product on click.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	@FXML
	void cancelProductOnClick(ActionEvent actionEvent) throws IOException {
		ReturnToMainWindow.mainWindow(actionEvent);
	}

	/**
	 * Initializes the Parts and Associated Parts table views.
	 *
	 * @param location  the location.
	 * @param resources the resources.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CommonFunctions.getPartsTable(partsTableView, partIDCol, partNameCol, partInvLvlCol,
				partCostCol, associatedPartsTableView, associatedParts, associatedPartIdCol,
				associatedPartNameCol, associatedPartInvLvlCol, associatedPartCostCol);
	}
}