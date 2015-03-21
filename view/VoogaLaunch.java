package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class VoogaLaunch extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		SceneManager author = new SceneManager();
		author.createScene(stage);
	}
}
