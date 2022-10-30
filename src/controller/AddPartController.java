package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

	@FXML
	private TextField partIdTextField;
	@FXML
	private TextField partNameTextField;
	@FXML
	private TextField partInvTextField;
	@FXML
	private TextField partPriceTextField;
	@FXML
	private TextField partMaxTextField;
	@FXML
	private TextField partMinTextField;
	@FXML
	private TextField partMacIdTextField;
	@FXML
	private RadioButton inHouseToggleBtn;
	@FXML
	private RadioButton outsourcedToggleBtn;
	@FXML
	private Label partMacIdLabel;
	@FXML
	private ErrMsg errMsg;


	@FXML
	void toggleInHouse() {
		partMacIdLabel.setText("Machine ID");
	}

	@FXML
	void toggleOutsource() {
		partMacIdLabel.setText("Company Name");
	}

	/**
	 * RUNTIME ERROR: Caused by: java.lang.NumberFormatException: For input string: ""
	 * The program wouldn't crash, but it would throw an error in the console when
	 * clicking save.
	 * The error description was vague, but it turns out that by not adding
	 * try() catch() blocks, the Part would not save.
	 *
	 * @throws IOException
	 */
	@FXML
	void savePartOnClick(ActionEvent actionEvent) throws IOException {
//		ObservableList<Part> allParts = Inventory.getAllParts();
		int id = Inventory.getAllParts().size() + 1;
		isValid();

		try {
//			errMsg = new ErrMsg(code);

			String partName = partNameTextField.getText();
			int partInv = Integer.parseInt(partInvTextField.getText());
			double partPrice = Double.parseDouble(partPriceTextField.getText());
			int partMax = Integer.parseInt(partMaxTextField.getText());
			int partMin = Integer.parseInt(partMinTextField.getText());

			if (!(verifyMin(partMin, partMax)) || !(verifyInv(partInv, partMin, partMax))) ;
			else {
				if (inHouseToggleBtn.isSelected()) {

					if (!(verifyInt(partMacIdTextField.getText())) || partMacIdTextField.getText().isEmpty()) {
						ErrMsg.displayErrMsg(8);
					}

					int partMachineId = Integer.parseInt(partMacIdTextField.getText());
					InHouse part = new InHouse(id, partName, partPrice, partInv, partMin, partMax, partMachineId);
					Inventory.addPart(part);
					mainWindow(actionEvent);
				} else {
					if ((partMacIdTextField.getText().isEmpty())) {
						ErrMsg.displayErrMsg(9);
					} else {
						String companyName = partMacIdTextField.getText();
						Outsourced part = new Outsourced(id, partName, partPrice, partInv, partMin, partMax, companyName);
						Inventory.addPart(part);
						mainWindow(actionEvent);
					}
				}
			}
		} catch (Exception e) {
//			isValid();
		}
	}


	/**
	 * Closes the current window.
	 * Returns to the main window.
	 *
	 * @param actionEvent triggered by clicking the cancel button.
	 */
	@FXML
	void cancelPartOnSave(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Inventory Management System");
		stage.setScene(scene);
		stage.show();
	}

	private boolean verifyMin(int min, int max) throws IOException {
		if (!(min > 0 && min < max)) {
			ErrMsg.displayErrMsg(3);
			return false;
		} else return true;
	}

	private boolean verifyInv(int inv, int min, int max) throws IOException {
		if (!(inv >= min && inv <= max)) {
			ErrMsg.displayErrMsg(2);
			return false;
		} else return true;
	}

	private boolean verifyDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean verifyInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void isValid() {
		if (partNameTextField.getText().isEmpty()) ErrMsg.displayErrMsg(1);
		else if (!(verifyInt(partInvTextField.getText())) || partInvTextField.getText().isEmpty())
			ErrMsg.displayErrMsg(4);
		else if (!(verifyDouble(partPriceTextField.getText())) || partPriceTextField.getText().isEmpty())
			ErrMsg.displayErrMsg(5);
		else if (!(verifyInt(partMaxTextField.getText())) || partMaxTextField.getText().isEmpty())
			ErrMsg.displayErrMsg(6);
		else if (!(verifyInt(partMinTextField.getText())) || partMinTextField.getText().isEmpty())
			ErrMsg.displayErrMsg(7);
	}

	private void mainWindow(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
			Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			Scene scene = new Scene(fxmlLoader.load());
			stage.setTitle("Inventory Management System");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void errMsg(int errCode) {
//		Alert err = new Alert(Alert.AlertType.ERROR);
//		String numMsg = "Please enter a valid number";
//		err.setTitle("Error");
//		if (errCode == 1) {
//			err.setHeaderText("Part name is empty.");
//			err.setContentText("Please enter a valid part name.");
//			err.showAndWait();
//		} else if (errCode == 2) {
//			err.setHeaderText("Inventory is not within the range of min and max");
//			err.setContentText("Please enter a valid inventory value!");
//			err.showAndWait();
//		} else if (errCode == 3) {
//			err.setHeaderText("Min must be less than max and greater than 0");
//			err.setContentText("Please enter a valid min value!");
//			err.showAndWait();
//		} else if (errCode == 4) {
//			err.setHeaderText("Inventory field is either empty or invalid");
//			err.setContentText(numMsg);
//			err.showAndWait();
//		} else if (errCode == 5) {
//			err.setHeaderText("Price field is either empty or invalid");
//			err.setContentText("Please enter a valid price");
//			err.showAndWait();
//		} else if (errCode == 6) {
//			err.setHeaderText("Max field is either empty or invalid");
//			err.setContentText(numMsg);
//			err.showAndWait();
//		} else if (errCode == 7) {
//			err.setHeaderText("Min field is either empty or invalid");
//			err.setContentText(numMsg);
//			err.showAndWait();
//		} else if (errCode == 8) {
//			err.setHeaderText("Machine ID field is either empty or invalid");
//			err.setContentText(numMsg);
//			err.showAndWait();
//		} else if (errCode == 9) {
//			err.setHeaderText("Company name field is either empty or invalid");
//			err.setContentText("Please enter a valid company name");
//			err.showAndWait();
//		}
//
//	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}
