package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReturnToMainWindow {
	/**
	 * Main window.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	public static void mainWindow(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ReturnToMainWindow.class.getResource("/view/MainScene.fxml"));
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
