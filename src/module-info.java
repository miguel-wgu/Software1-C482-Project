module miguel.software1c482project {
	requires javafx.controls;
	requires javafx.fxml;


	opens controller to javafx.fxml;
	exports controller;
	exports main;

}