package controller;

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
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for Main.fxml.
 * <p>
 * Provides functionality for the main screen.
 *
 * @author Miguel Guzman
 */

public class MainController implements Initializable {


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
	private TableView<Product> productsTableView;
	@FXML
	private TableColumn<Product, Integer> productIDCol;
	@FXML
	private TableColumn<Product, String> productNameCol;
	@FXML
	private TableColumn<Product, Integer> productInvLvlCol;
	@FXML
	private TableColumn<Product, Double> productCostCol;


	// delete this line

	private ObservableList<Part> allParts = Inventory.getAllParts();
	private ObservableList<Product> allProducts = Inventory.getAllProducts();
	InHouse part1 = new InHouse(1, "test", 1.00, 1, 1, 1, 1);
	InHouse part2 = new InHouse(2, "test2", 2.00, 2, 2, 2, 2);
	InHouse part3 = new InHouse(3, "test3", 3.00, 3, 3, 3, 3);

	public void initialize(URL url, ResourceBundle resourceBundle) {
		allParts.add(part1);
		allParts.add(part2);
		allParts.add(part3);
		partsTableView.setItems(allParts);
		partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		partInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		productsTableView.setItems(allProducts);
		productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		productInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	/**
	 * RUNTIME ERROR
	 * <p>
	 * Originally defined exitOnClick in controller.
	 * Made some changes to the mainView.fxml file and
	 * exitOnClick was no longer linked between the
	 * MainController and MainScene.
	 * Code would not run and it took quite a while to
	 * figure it out. I had to look for types while also
	 * trying to make sense of the error message.
	 * <p>
	 * Exit button functionality.
	 */
	@FXML
	void exitOnClick() {
		System.exit(0);
	}

	/**
	 * Loads the Add Part window.
	 *
	 * @param actionEvent defines the action of the add button.
	 * @throws IOException if there is an issue with FXMLoader.
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
	 * Loads the Modify Part window.
	 *
	 * @param actionEvent defines the action of the modify button.
	 * @throws IOException if there is no part selected.
	 */
	@FXML
	void partModifyOnClick(ActionEvent actionEvent) throws IOException {
		Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
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
	 *
	 * @param actionEvent defines the action of the delete button.
	 *                    If the user selects "OK" the part will be deleted.
	 *                    If the user selects "Cancel" the part will not be deleted.
	 *                    If the user does not select anything it will throw an error.
	 */
	@FXML
	void partDeleteOnClick(ActionEvent actionEvent) {
		Part part = partsTableView.getSelectionModel().getSelectedItem();
		try {
			if (part != null) {
				Alert confirm = createAlert();
				confirm.setContentText("Are you sure you want to delete this part?");
				Optional<ButtonType> input = confirm.showAndWait();
				if (input.isPresent() && input.get() == ButtonType.OK) Inventory.deletePart(part);
			} else throw new NullPointerException();
		} catch (NullPointerException e) {
			ErrMsg.displayErrMsg(11);
		}
	}

	@FXML
	Alert createAlert() {
		Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
		confirm.setTitle("Confirm Delete");
		confirm.setHeaderText("Warning!");
		return confirm;
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


	@FXML
	void productAddOnClick(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddProductScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Add Product");
		stage.setScene(scene);
	}

	@FXML
	void productModifyOnClick(ActionEvent actionEvent) throws IOException {
		Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
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


	@FXML
	void productDeleteOnClick(ActionEvent actionEvent) {
		Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
//		try {
		if (selectedProduct != null) {
			Alert confirm = createAlert();
			confirm.setContentText("Are you sure you want to delete this product?");
			Optional<ButtonType> input = confirm.showAndWait();
			if (input.isPresent() && input.get() == ButtonType.OK) {
				ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();
				System.out.println(associatedParts.size());
				System.out.println("Hello");
				if ((associatedParts.isEmpty())) Inventory.deleteProduct(selectedProduct);
				else {
					System.out.println("err");
				}
			}
		} else throw new NullPointerException();
//		} catch (NullPointerException e) {
//			ErrMsg.displayErrMsg(12);
//		}
	}


	@FXML
	void productSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Product> searchResults = Inventory.getAllProducts();
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