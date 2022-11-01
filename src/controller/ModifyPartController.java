package controller;

import helper.CommonFunctions;
import helper.ErrMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static helper.ErrMsg.*;

/**
 * The type Modify part controller.
 * <br><br>
 * Provides functionality for the Modify Part window.
 *
 * @author Miguel Guzman
 */
public class ModifyPartController implements Initializable {
	/**
	 * The Selected part.
	 */
	private Part selectedPart;
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
	 * The Part mac id label.
	 */
	@FXML
	private Label partMacIdLabel;
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
	 * When selected, the text will show "Machine ID."
	 */
	public void toggleInHouse() {
		partMacIdLabel.setText("Machine ID");
	}

	/**
	 * When selected, the text will show "Company Name."
	 */
	public void toggleOutsource() {
		partMacIdLabel.setText("Company Name");
	}

	/**
	 * Save parts part if validation checks are passed.
	 * Included appropriate error messages. Includes
	 * logger to log errors just to get some practice
	 * with them.
	 *
	 * @param actionEvent the action event
	 */
	public void savePartOnClick(ActionEvent actionEvent) {
		int id = selectedPart.getId();
		isValid(partNameTextField, partInvTextField, partPriceTextField, partMaxTextField, partMinTextField);
		try {
			String partName = partNameTextField.getText();
			int partInv = Integer.parseInt(partInvTextField.getText());
			double partPrice = Double.parseDouble(partPriceTextField.getText());
			int partMax = Integer.parseInt(partMaxTextField.getText());
			int partMin = Integer.parseInt(partMinTextField.getText());

			if (!(ErrMsg.verifyMin(partMin, partMax)) || !(ErrMsg.verifyInv(partInv, partMin, partMax))) {
				System.Logger logger = System.getLogger("ModifyPartController");
				logger.log(System.Logger.Level.ERROR, "Invalid input");
			} else {
				if (inHouseToggleBtn.isSelected() && verifyMacId(partMacIdTextField)) {
					int partMachineId = Integer.parseInt(partMacIdTextField.getText());
					InHouse part = new InHouse(id, partName, partPrice, partInv, partMin, partMax, partMachineId);
					Inventory.addPart(part);
					Inventory.deletePart(selectedPart);
					CommonFunctions.mainWindow(actionEvent);
				} else if (outsourcedToggleBtn.isSelected() && verifyCompName(partMacIdTextField)) {
					String companyName = partMacIdTextField.getText();
					Outsourced part = new Outsourced(id, partName, partPrice, partInv, partMin, partMax, companyName);
					Inventory.addPart(part);
					Inventory.deletePart(selectedPart);
					CommonFunctions.mainWindow(actionEvent);
				}
			}
		} catch (NumberFormatException | IOException e) {
			System.Logger logger = System.getLogger("ModifyPartController");
			logger.log(System.Logger.Level.ERROR, "Error: " + e.getMessage());
		}
	}

	/**
	 * Cancel part on click.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	public void cancelPartOnClick(ActionEvent actionEvent) throws IOException {
		CommonFunctions.mainWindow(actionEvent);
	}

	/**
	 * Initializes the Modify Part window.
	 * Fills in the text fields with the selected part's information.
	 *
	 * @param url            the url.
	 * @param resourceBundle the resource bundle.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedPart = MainController.getSelectedPart();
		partIdTextField.setText(Integer.toString(selectedPart.getId()));
		partNameTextField.setText(selectedPart.getName());
		partInvTextField.setText(Integer.toString(selectedPart.getStock()));
		partPriceTextField.setText(Double.toString(selectedPart.getPrice()));
		partMaxTextField.setText(Integer.toString(selectedPart.getMax()));
		partMinTextField.setText(Integer.toString(selectedPart.getMin()));
		if (selectedPart instanceof InHouse part) {
			partMacIdTextField.setText(Integer.toString((part).getMachineId()));
			inHouseToggleBtn.setSelected(true);
			partMacIdLabel.setText("Machine ID");
		} else if (selectedPart instanceof Outsourced part) {
			partMacIdTextField.setText((part).getCompanyName());
			outsourcedToggleBtn.setSelected(true);
			partMacIdLabel.setText("Company Name");
		}
	}
}
