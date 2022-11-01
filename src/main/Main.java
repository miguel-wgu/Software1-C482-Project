package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/********IMPROVEMENTS********
 *<br>
 * A future improvement would be to de-clutter the code would be to
 * transfer the Products table code (under initialize) over
 * to the CommonFunctions class.
 *<br><br>
 * Another future improvement would be to incorporate a CSV file or
 * a small database so that the user can save their data.
 * <br><br>
 * Javadocs can be fund in src/javadocs
 * <br><br>
 * The Main class is the entry point of the application.
 *
 * @author Miguel Guzman
 */
public class Main extends Application {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments.
	 */
	public static void main(String[] args) {
		launch();
	}

	/**
	 * Start sets up the stage and loads the main view.
	 *
	 * @param stage the stage.
	 * @throws IOException the io exception.
	 */
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainScene.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 1024, 550);
		stage.setTitle("Inventory Management System");
		stage.setScene(scene);
		stage.show();
	}
}