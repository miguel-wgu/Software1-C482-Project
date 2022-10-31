package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;

public class CommonFunctions {

	public static void getPartsTable(TableView<Part> partsTableView, TableColumn<Part, Integer> partIDCol,
	                                 TableColumn<Part, String> partNameCol, TableColumn<Part, Integer> partInvLvlCol,
	                                 TableColumn<Part, Double> partCostCol,
	                                 TableView<Part> associatedPartsTableView, ObservableList<Part> associatedParts,
	                                 TableColumn<Part, Integer> associatedPartIdCol, TableColumn<Part, String> associatedPartNameCol,
	                                 TableColumn<Part, Integer> associatedPartInvLvlCol,
	                                 TableColumn<Part, Double> associatedPartCostCol) {
		partsTableView.setItems(Inventory.getAllParts());
		partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		partInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		getAssociatedPartsTable(associatedPartsTableView, associatedParts, associatedPartIdCol, associatedPartNameCol, associatedPartInvLvlCol, associatedPartCostCol);
	}

	public static void getAssociatedPartsTable(TableView<Part> associatedPartsTableView, ObservableList<Part> associatedParts, TableColumn<Part, Integer> associatedPartIdCol, TableColumn<Part, String> associatedPartNameCol, TableColumn<Part, Integer> associatedPartInvLvlCol, TableColumn<Part, Double> associatedPartCostCol) {
		associatedPartsTableView.setItems(associatedParts);
		associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		associatedPartInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
		associatedPartCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	public static void getSearchResults(TextField search, String input, ObservableList<Part> allParts, TableView<Part> partsTableView) {
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
}
