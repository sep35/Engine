package view;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadNSave {
		private FileChooser fileChooser;
		
		public LoadNSave(){
			fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		}
		
		public Path read() {
			fileChooser.setTitle("Read data");
			File dataText = fileChooser.showOpenDialog(new Stage());
			Path path = Paths.get(dataText.getPath());
			return path;
		}
		
		public Path write() {
			fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			fileChooser.setTitle("Save Data");
			File newTextFile = fileChooser.showSaveDialog(new Stage());
			return Paths.get(newTextFile.getPath());
		}
}

