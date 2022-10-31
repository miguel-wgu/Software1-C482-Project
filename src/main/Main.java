package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
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