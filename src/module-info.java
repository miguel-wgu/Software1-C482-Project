module miguel.software1c482project {
	requires javafx.controls;
	requires javafx.fxml;


	opens controller to javafx.fxml;
	opens model to javafx.base;
	exports controller;
	exports main;
	exports helper;
	exports model;
	opens helper to javafx.fxml;

}