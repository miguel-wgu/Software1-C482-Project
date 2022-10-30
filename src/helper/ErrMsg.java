package helper;

import controller.AddPartController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Helper class for error messages.
 *
 * @author Miguel Guzman
 */
public class ErrMsg {
	/**
	 * Constant err to avoid repetition of new Alert.
	 */
	private static final Alert err = new Alert(Alert.AlertType.ERROR);

	/**
	 * Display error message.
	 *
	 * @param errCode err code to display.
	 */
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

	public static void isValid(TextField partNameField, TextField partInvField, TextField partPriceField, TextField partMaxField, TextField partMinField) {
		if (partNameField.getText().isEmpty()) displayErrMsg(1);
		else if (!(verifyInt(partInvField.getText())) || partInvField.getText().isEmpty())
			ErrMsg.displayErrMsg(4);
		else if (!(verifyDouble(partPriceField.getText())) || partPriceField.getText().isEmpty())
			ErrMsg.displayErrMsg(5);
		else if (!(verifyInt(partMaxField.getText())) || partMaxField.getText().isEmpty())
			ErrMsg.displayErrMsg(6);
		else if (!(verifyInt(partMinField.getText())) || partMinField.getText().isEmpty())
			ErrMsg.displayErrMsg(7);
	}

	public static boolean verifyMacId(TextField partMacIdField) {
		if (!(verifyInt(partMacIdField.getText())) || partMacIdField.getText().isEmpty()){
			ErrMsg.displayErrMsg(8);
			return false;
		} return true;
	}

	public static boolean verifyCompName(TextField partCompNameField) {
		if (partCompNameField.getText().isEmpty()) {
			ErrMsg.displayErrMsg(9);
			return false;
		} return true;
	}

	public static boolean verifyInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean verifyDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Verify min boolean.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the boolean
	 */
	public static boolean verifyMin(int min, int max) {
		if (!(min > 0 && min < max)) {
			ErrMsg.displayErrMsg(3);
			return false;
		} else return true;
	}

	/**
	 * Verify inv boolean.
	 *
	 * @param inv the inv
	 * @param min the min
	 * @param max the max
	 * @return the boolean
	 */
	public static boolean verifyInv(int inv, int min, int max) {
		if (!(inv >= min && inv <= max)) {
			ErrMsg.displayErrMsg(2);
			return false;
		} else return true;
	}
}