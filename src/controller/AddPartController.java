package controller;

import helper.ErrMsg;
import helper.ReturnToMainWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static helper.ErrMsg.*;

/**
 * Controller class for AddPart.fxml.
 * <p>
 * Provides functionality for the add part screen.
 *
 * @author Miguel Guzman
 */
public class AddPartController implements Initializable {
	/**
	 * The Part id text field.
	 */
	@FXML
	private TextField partIdTextField;
	/**
	 * The Part name text field.
	 */
	@FXML
	private TextField partNameTextField;
	/**
	 * The Part inv text field.
	 */
	@FXML
	private TextField partInvTextField;
	/**
	 * The Part price text field.
	 */
	@FXML
	private TextField partPriceTextField;
	/**
	 * The Part max text field.
	 */
	@FXML
	private TextField partMaxTextField;
	/**
	 * The Part min text field.
	 */
	@FXML
	private TextField partMinTextField;
	/**
	 * The Part mac id text field.
	 */
	@FXML
	private TextField partMacIdTextField;
	/**
	 * The In house toggle btn.
	 */
	@FXML
	private RadioButton inHouseToggleBtn;
	/**
	 * The Outsourced toggle btn.
	 */
	@FXML
	private RadioButton outsourcedToggleBtn;
	/**
	 * The Part mac id label.
	 */
	@FXML
	private Label partMacIdLabel;

	/**
	 * When selected, the text will show "Machine ID".
	 */
	@FXML
	void toggleInHouse() {
		partMacIdLabel.setText("Machine ID");
	}

	/**
	 * When selected, the text will show "Company Name".
	 */
	@FXML
	void toggleOutsource() {
		partMacIdLabel.setText("Company Name");
	}

	/**
	 * Save parts part if validation checks are passed.
	 * Included appropriate error messages. Includes
	 * logger to log errors just to get some practice
	 * with them.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	@FXML
	void savePartOnClick(ActionEvent actionEvent) throws IOException {
		int id = Inventory.getAllParts().size() + 1;
		isValid(partNameTextField, partInvTextField, partPriceTextField, partMaxTextField, partMinTextField);

		try {
			String partName = partNameTextField.getText();
			int partInv = Integer.parseInt(partInvTextField.getText());
			double partPrice = Double.parseDouble(partPriceTextField.getText());
			int partMax = Integer.parseInt(partMaxTextField.getText());
			int partMin = Integer.parseInt(partMinTextField.getText());

			if (!(ErrMsg.verifyMin(partMin, partMax)) || !(ErrMsg.verifyInv(partInv, partMin, partMax))) {
				System.Logger logger = System.getLogger("AddPartController");
				logger.log(System.Logger.Level.ERROR, "Invalid input");
			} else {
				if (inHouseToggleBtn.isSelected() && verifyMacId(partMacIdTextField)) {
					int partMachineId = Integer.parseInt(partMacIdTextField.getText());
					InHouse part = new InHouse(id, partName, partPrice, partInv, partMin, partMax, partMachineId);
					Inventory.addPart(part);
					ReturnToMainWindow.mainWindow(actionEvent);
				} else if (outsourcedToggleBtn.isSelected() && verifyCompName(partMacIdTextField)) {
					String companyName = partMacIdTextField.getText();
					Outsourced part = new Outsourced(id, partName, partPrice, partInv, partMin, partMax, companyName);
					Inventory.addPart(part);
					ReturnToMainWindow.mainWindow(actionEvent);
				}
			}
		} catch (NumberFormatException e) {
			System.Logger logger = System.getLogger("AddPartController");
			logger.log(System.Logger.Level.ERROR, "Error: " + e.getMessage());
		}
	}

	/**
	 * Closes the current window.
	 * Returns to the main window.
	 *
	 * @param actionEvent triggered by clicking the cancel button.
	 * @throws IOException the io exception.
	 */
	@FXML
	void cancelPartOnClick(ActionEvent actionEvent) throws IOException {
		ReturnToMainWindow.mainWindow(actionEvent);
	}

	/**
	 * Initialize.
	 *
	 * @param url            the url
	 * @param resourceBundle the resource bundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Empty because there is no need to initialize anything.
	}
}