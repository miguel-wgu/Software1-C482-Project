package controller;

import helper.CommonFunctions;
import helper.ErrMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/********RUNTIME ERROR********
 *<br>
 * Originally defined exitOnClick in controller.
 * Made some changes to the mainView.fxml file and
 * exitOnClick was no longer linked between the
 * MainController and MainScene.
 * Code would not run, and it took quite a while to
 * figure it out. I had to look for types while also
 * trying to make sense of the error message.
 * <br><br>
 * Controller class for Main.fxml.
 * <br><br>
 * Provides functionality for the main screen.
 *
 * @author Miguel Guzman
 */
public class MainController implements Initializable {

	/**
	 * The constant selectedPart.
	 */
	private static Part selectedPart;
	/**
	 * The constant selectedProduct.
	 */
	private static Product selectedProduct;
	/**
	 * List used to hold parts to be displayed in the table.
	 */
	private final ObservableList<Part> allParts = Inventory.getAllParts();
	/**
	 * List used to hold products to be displayed in the table.
	 */
	private final ObservableList<Product> allProducts = Inventory.getAllProducts();
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
	 * The Products table view.
	 */
	@FXML
	private TableView<Product> productsTableView;
	/**
	 * The Product id col.
	 */
	@FXML
	private TableColumn<Product, Integer> productIDCol;
	/**
	 * The Product name col.
	 */
	@FXML
	private TableColumn<Product, String> productNameCol;
	/**
	 * The Product inv lvl col.
	 */
	@FXML
	private TableColumn<Product, Integer> productInvLvlCol;
	/**
	 * The Product cost col.
	 */
	@FXML
	private TableColumn<Product, Double> productCostCol;

	/**
	 * Gets selected part when part is selected in the parts table.
	 *
	 * @return the selected part
	 */
	public static Part getSelectedPart() {
		return selectedPart;
	}

	/**
	 * Gets selected product when product is selected in the products table.
	 *
	 * @return the selected product
	 */
	public static Product getSelectedProduct() {
		return selectedProduct;
	}

	/**
	 * Initialize.
	 *
	 * @param url            the url
	 * @param resourceBundle the resource bundle
	 */
	public void initialize(URL url, ResourceBundle resourceBundle) {
		CommonFunctions.getAssociatedPartsTable(partsTableView, allParts, partIDCol, partNameCol, partInvLvlCol, partCostCol);
		productsTableView.setItems(allProducts);
		productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		productInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	/**
	 * Exit application on click.
	 */
	@FXML
	void exitOnClick() {
		System.exit(0);
	}

	/**
	 * Opens Add Part window on click.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	@FXML
	void partAddOnClick(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPartScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Add Part");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Opens Part Modify window on click.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	@FXML
	void partModifyOnClick(ActionEvent actionEvent) throws IOException {
		selectedPart = partsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedPart != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartScene.fxml"));
				Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
				Scene scene = new Scene(loader.load());
				stage.setTitle("Modify Part");
				stage.setScene(scene);
				stage.show();
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(11);
		}
	}

	/**
	 * Deletes a part from the parts table.
	 * <p>
	 * If the user selects "OK" the part will be deleted.
	 * If the user selects "Cancel" the part will not be deleted.
	 * If the user does not select anything it will throw an error.
	 */
	@FXML
	void partDeleteOnClick() {
		Part part = partsTableView.getSelectionModel().getSelectedItem();
		try {
			if (part != null) {
				Alert confirm = ErrMsg.createAlert();
				confirm.setContentText("Are you sure you want to delete this part?");
				Optional<ButtonType> input = confirm.showAndWait();
				if (input.isPresent() && input.get() == ButtonType.OK) Inventory.deletePart(part);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(11);
		}
	}

	/**
	 * Searches for part by name or ID.
	 * <p>
	 * If no part is found, an error message will be displayed,
	 * the search field will clear, and the table will be refreshed.
	 *
	 * @param actionEvent defines the action of the search field.
	 */
	@FXML
	void partSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		CommonFunctions.getSearchResults(search, input, allParts, partsTableView);
	}


	/**
	 * Opens Add Product window on click.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	@FXML
	void productAddOnClick(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddProductScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Add Product");
		stage.setScene(scene);
	}

	/**
	 * Opens Modify Product window on click.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	@FXML
	void productModifyOnClick(ActionEvent actionEvent) throws IOException {
		selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedProduct != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyProductScene.fxml"));
				Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
				Scene scene = new Scene(loader.load());
				stage.setTitle("Modify Product");
				stage.setScene(scene);
				stage.show();
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(12);
		}
	}


	/**
	 * Product delete on click.
	 * <br>
	 * If no product is selected, an error message
	 * will be displayed.
	 * <br>
	 * User must confirm deletion of product.
	 * If product has associated parts, user will
	 * be notified and product will not be deleted.
	 */
	@FXML
	void productDeleteOnClick() {
		selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
		try {
			if (selectedProduct != null) {
				Alert confirm = ErrMsg.createAlert();
				confirm.setContentText("Are you sure you want to delete this product?");
				Optional<ButtonType> input = confirm.showAndWait();
				if (input.isPresent() && input.get() == ButtonType.OK) {
					ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();
					if ((associatedParts.isEmpty())) Inventory.deleteProduct(selectedProduct);
					else {
						ErrMsg.displayErrMsg(15);
					}
				}
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(12);
		}
	}

	/**
	 * Product search on key press.
	 * <p>
	 * Returns a list of products that match the search criteria.
	 *
	 * @param actionEvent the action event
	 */
	@FXML
	void productSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Product> searchResults = FXCollections.observableArrayList();
		for (Product product : allProducts) {
			if (product.getName().contains(input) || Integer.toString(product.getId()).contains(input))
				searchResults.add(product);
		}
		productsTableView.setItems(searchResults);
		if (searchResults.isEmpty()) {
			ErrMsg.displayErrMsg(13);
			productsTableView.setItems(allProducts);
			search.clear();
		}
	}
}