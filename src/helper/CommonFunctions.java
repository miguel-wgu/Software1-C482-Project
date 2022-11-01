package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;

/**
 * A helper class for common functions to reduce code repetition.
 *
 * @author Miguel Guzman
 */
public class CommonFunctions {
	private CommonFunctions() {
	}

	/**
	 * Gets parts table.
	 *
	 * @param partsTableView           the parts table view.
	 * @param partIDCol                the part id col.
	 * @param partNameCol              the part name col.
	 * @param partInvLvlCol            the part inv lvl col.
	 * @param partCostCol              the part cost col.
	 * @param associatedPartsTableView the associated parts table view.
	 * @param associatedParts          the associated parts.
	 * @param associatedPartIdCol      the associated part id col.
	 * @param associatedPartNameCol    the associated part name col.
	 * @param associatedPartInvLvlCol  the associated part inv lvl col.
	 * @param associatedPartCostCol    the associated part cost col.
	 */
	public static void getPartsTable(TableView<Part> partsTableView,
	                                 TableColumn<Part, Integer> partIDCol,
	                                 TableColumn<Part, String> partNameCol,
	                                 TableColumn<Part, Integer> partInvLvlCol,
	                                 TableColumn<Part, Double> partCostCol,
	                                 TableView<Part> associatedPartsTableView,
	                                 ObservableList<Part> associatedParts,
	                                 TableColumn<Part, Integer> associatedPartIdCol,
	                                 TableColumn<Part, String> associatedPartNameCol,
	                                 TableColumn<Part, Integer> associatedPartInvLvlCol,
	                                 TableColumn<Part, Double> associatedPartCostCol) {
		partsTableView.setItems(Inventory.getAllParts());
		partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		partInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		getAssociatedPartsTable(associatedPartsTableView, associatedParts, associatedPartIdCol,
				associatedPartNameCol, associatedPartInvLvlCol, associatedPartCostCol);
	}

	/**
	 * Gets associated parts table.
	 *
	 * @param associatedPartsTableView the associated parts table view.
	 * @param associatedParts          the associated parts.
	 * @param associatedPartIdCol      the associated part id col.
	 * @param associatedPartNameCol    the associated part name col.
	 * @param associatedPartInvLvlCol  the associated part inv lvl col.
	 * @param associatedPartCostCol    the associated part cost col.
	 */
	public static void getAssociatedPartsTable(TableView<Part> associatedPartsTableView,
	                                           ObservableList<Part> associatedParts,
	                                           TableColumn<Part, Integer> associatedPartIdCol,
	                                           TableColumn<Part, String> associatedPartNameCol,
	                                           TableColumn<Part, Integer> associatedPartInvLvlCol,
	                                           TableColumn<Part, Double> associatedPartCostCol) {
		associatedPartsTableView.setItems(associatedParts);
		associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		associatedPartInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		associatedPartCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	/**
	 * Gets search results.
	 *
	 * @param search         the search.
	 * @param input          the input.
	 * @param allParts       the all parts.
	 * @param partsTableView the parts table view.
	 */
	public static void getSearchResults(TextField search,
	                                    String input,
	                                    ObservableList<Part> allParts,
	                                    TableView<Part> partsTableView) {
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

	/**
	 * Returns to Main Window.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	public static void mainWindow(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(CommonFunctions.class.getResource("/view/MainScene.fxml"));
			Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			Scene scene = new Scene(fxmlLoader.load());
			stage.setTitle("Inventory Management System");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}