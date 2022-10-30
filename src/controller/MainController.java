package controller;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for Main.fxml.
 *
 * Provides functionality for the main screen.
 *
 * @author Miguel Guzman
 */

public class MainController implements Initializable {


	@FXML private TableView<Part> partsTableView;
	@FXML private TableColumn<Part, Integer> partIDCol;
	@FXML private TableColumn<Part, String> partNameCol;
	@FXML private TableColumn<Part, Integer> partInvLvlCol;
	@FXML private TableColumn<Part, Double> partCostCol;

	@FXML private TableView<Product> productsTableView;
	@FXML private TableColumn<Product, Integer> productIDCol;
	@FXML private TableColumn<Product, String> productNameCol;
	@FXML private TableColumn<Product, Integer> productInvLvlCol;
	@FXML private TableColumn<Product, Double> productCostCol;

	private static Part partToModify;



	// delete this line

	private ObservableList<Part> allParts = Inventory.getAllParts();
	private ObservableList<Product> allProducts = javafx.collections.FXCollections.observableArrayList();
//	InHouse part1 = new InHouse(1, "test", 1.0, 1, 1, 1, 1);
//	InHouse part2 = new InHouse(2, "test2", 2.0, 2, 2, 2, 2);
//	Product product1 = new Product(1, "test", 1.0, 1, 1, 1);
//	Product product2 = new Product(2, "test2", 2.0, 2, 2, 2);

	boolean flag = true;

//	private void sampleData() {
//		if (!flag) { return;
//		} else {
//			for(Part p : allParts) {
//				allParts.add(Inventory.getAllParts());
//			}
//			allParts.add(part2);
//			allProducts.add(product1);
//			allProducts.add(product2);
//			flag = false;
//		}
//	}

	public void initialize(URL url, ResourceBundle resourceBundle) {
//		sampleData();
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
	 *
	 * Originally defined exitOnClick in controller.
	 * Made some changes to the mainView.fxml file and
	 * exitOnClick was no longer linked between the
	 * MainController and MainScene.
	 * Code would not run and it took quite a while to
	 * figure it out. I had to look for types while also
	 * trying to make sense of the error message.
	 *
	 * Exit button functionality.
	 *
	 */
	@FXML void exitOnClick() {
		System.exit(0);
	}

	/**
	 * Loads the Add Part window.
	 *
	 * @param actionEvent defines the action of the add button.
	 * @throws IOException if there is an issue with FXMLoader.
	 */
	@FXML void partAddOnClick(ActionEvent actionEvent) throws IOException {
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
	@FXML void partModifyOnClick(ActionEvent actionEvent) throws IOException {
		partToModify = partsTableView.getSelectionModel().getSelectedItem();
		if (partsTableView.getSelectionModel().getSelectedItem() != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartScene.fxml"));
			Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			Scene scene = new Scene(loader.load());
			stage.setTitle("Modify Part");
			stage.setScene(scene);
			stage.show();
		} else if (partToModify == null) errMsg(1);

	}

	/**
	 * Deletes a part from the parts table.
	 *
	 * @param actionEvent defines the action of the delete button.
	 * If the user selects "OK" the part will be deleted.
	 * If the user selects "Cancel" the part will not be deleted.
	 * If the user does not select anything it will throw an error.
	 */
	@FXML void partDeleteOnClick(ActionEvent actionEvent) {
		Part part = partsTableView.getSelectionModel().getSelectedItem();
		if (part != null) {
			Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
			confirm.setTitle("Confirm Delete");
			confirm.setHeaderText("Warning!");
			confirm.setContentText("Are you sure you want to delete this part?");
			Optional<ButtonType> input = confirm.showAndWait();
			if (input.isPresent() && input.get() == ButtonType.OK) allParts.remove(part);
		}
		else errMsg(1);
	}

	/**
	 * Searches for part by name or ID.
	 *
	 * If no part is found, an error message will be displayed,
	 * the search field will clear, and the table will be refreshed.
	 *
	 * @param actionEvent defines the action of the search field.
	 *
	 */
	@FXML void partSearchOnKeyPress(ActionEvent actionEvent) {
		TextField search = (TextField) actionEvent.getSource();
		String input = search.getText();
		ObservableList<Part> searchResults = FXCollections.observableArrayList();
		for (Part part : allParts) {
			if (part.getName().contains(input) || Integer.toString(part.getId()).contains(input)) searchResults.add(part);
		}
		partsTableView.setItems(searchResults);
		if (searchResults.isEmpty()) {
			errMsg(2);
			partsTableView.setItems(allParts);
			search.clear();
		}
	}

	@FXML void productAddOnClick(ActionEvent actionEvent) {

	}

	@FXML void productModifyOnClick(ActionEvent actionEvent) {

	}

	@FXML void productDeleteOnClick(ActionEvent actionEvent) {

	}

	@FXML void productSearchOnKeyPress(ActionEvent actionEvent) {

	}

	private void errMsg(int errCode) {
		Alert info = new Alert(Alert.AlertType.INFORMATION);
		Alert err = new Alert(Alert.AlertType.ERROR);

		if (errCode == 1) {
			err.setTitle("An error has occurred!");
			err.setHeaderText("Please select a part!");
			err.showAndWait();
		} else if (errCode == 2) {
			info.setTitle("Not Found");
			info.setHeaderText("The part you are looking for was not found.");
			info.showAndWait();
		}
	}
}


//
//	private void displayAlert(int alertType) {
//
//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		Alert alertError = new Alert(Alert.AlertType.ERROR);
//
//		switch (alertType) {
//			case 1:
//				alert.setTitle("Information");
//				alert.setHeaderText("Part not found");
//				alert.showAndWait();
//				break;
//			case 2:
//				alert.setTitle("Information");
//				alert.setHeaderText("Product not found");
//				alert.showAndWait();
//				break;
//			case 4:
//				alertError.setTitle("Error");
//				alertError.setHeaderText("Product not selected");
//				alertError.showAndWait();
//				break;
//			case 5:
//				alertError.setTitle("Error");
//				alertError.setHeaderText("Parts Associated");
//				alertError.setContentText("All parts must be removed from product before deletion.");
//				alertError.showAndWait();
//				break;
//		}
//	}
