package controller;

import javafx.scene.control.Alert;

public class ErrMsg {
	private static Alert err = new Alert(Alert.AlertType.ERROR);
	public int errCode;

//	public ErrMsg() {
//	}

	public int getErrCode() {
		return errCode;
	}

	public static void displayErrMsg(int errCode) {
		String numMsg = "Please enter a valid number";
		err.setTitle("Error");
		if (errCode == 1) {
			err.setHeaderText("Part name is empty.");
			err.setContentText("Please enter a valid part name.");
			err.showAndWait();
		} else if (errCode == 2) {
			err.setHeaderText("Inventory is not within the range of min and max");
			err.setContentText("Please enter a valid inventory value!");
			err.showAndWait();
		} else if (errCode == 3) {
			err.setHeaderText("Min must be less than max and greater than 0");
			err.setContentText("Please enter a valid min value!");
			err.showAndWait();
		} else if (errCode == 4) {
			err.setHeaderText("Inventory field is either empty or invalid");
			err.setContentText(numMsg);
			err.showAndWait();
		} else if (errCode == 5) {
			err.setHeaderText("Price field is either empty or invalid");
			err.setContentText("Please enter a valid price");
			err.showAndWait();
		} else if (errCode == 6) {
			err.setHeaderText("Max field is either empty or invalid");
			err.setContentText(numMsg);
			err.showAndWait();
		} else if (errCode == 7) {
			err.setHeaderText("Min field is either empty or invalid");
			err.setContentText(numMsg);
			err.showAndWait();
		} else if (errCode == 8) {
			err.setHeaderText("Machine ID field is either empty or invalid");
			err.setContentText(numMsg);
			err.showAndWait();
		} else if (errCode == 9) {
			err.setHeaderText("Company name field is either empty or invalid");
			err.setContentText("Please enter a valid company name");
			err.showAndWait();
		}
	}

}
