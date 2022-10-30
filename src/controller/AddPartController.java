package controller;

import helper.ErrMsg;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static helper.ErrMsg.*;

/**
 * Controller class for AddPart.fxml.
 *
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
	 * The Err msg.
	 */
	@FXML
	private ErrMsg errMsg;


	/**
	 * Toggle in house.
	 */
	@FXML
	void toggleInHouse() {
		partMacIdLabel.setText("Machine ID");
	}

	/**
	 * Toggle outsource.
	 */
	@FXML
	void toggleOutsource() {
		partMacIdLabel.setText("Company Name");
	}

	/**
	 * Save part.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
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
					mainWindow(actionEvent);
					System.out.println("part added");
				} else if (outsourcedToggleBtn.isSelected() && verifyCompName(partMacIdTextField)) {
					String companyName = partMacIdTextField.getText();
					Outsourced part = new Outsourced(id, partName, partPrice, partInv, partMin, partMax, companyName);
					Inventory.addPart(part);
					mainWindow(actionEvent);
					System.out.println("part added");
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
	 * @throws IOException the io exception
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


//
//	/**
//	 * Verify double boolean.
//	 *
//	 * @param str the str
//	 * @return the boolean
//	 */
//	private boolean verifyDouble(String str) {
//		try {
//			Double.parseDouble(str);
//			return true;
//		} catch (NumberFormatException e) {
//			return false;
//		}
//	}
//
//	/**
//	 * Verify int boolean.
//	 *
//	 * @param str the str
//	 * @return the boolean
//	 */
//	private boolean verifyInt(String str) {
//		try {
//			Integer.parseInt(str);
//			return true;
//		} catch (NumberFormatException e) {
//			return false;
//		}
//	}


//	private void isValid(){
//		if (partNameTextField.getText().isEmpty()) ErrMsg.displayErrMsg(1);
//		else if (!(verifyInt(partInvTextField.getText())) || partInvTextField.getText().isEmpty())
//			ErrMsg.displayErrMsg(4);
//		else if (!(verifyDouble(partPriceTextField.getText())) || partPriceTextField.getText().isEmpty())
//			ErrMsg.displayErrMsg(5);
//		else if (!(verifyInt(partMaxTextField.getText())) || partMaxTextField.getText().isEmpty())
//			ErrMsg.displayErrMsg(6);
//		else if (!(verifyInt(partMinTextField.getText())) || partMinTextField.getText().isEmpty())
//			ErrMsg.displayErrMsg(7);
//	}

	/**
	 * Main window.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
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

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}
