package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SimpleDialog extends Stage {
	
	/**
	 * CUSTOM MESSAGE DIALOG WINDOW
	 * @param title
	 * @param message
	 * @param width
	 * @param height
	 * @param icon
	 */
	public SimpleDialog(String title, String message, int width, int height) {
		super();
		
		/* get stylesheet path */
		String stylesheet = SimpleDialog.class.getResource("Style.css").toExternalForm();
		
		/* layout */
		BorderPane layout = new BorderPane();
		
		/* layout -> center */
		TextArea textArea = new TextArea(message);
		textArea.setWrapText(true);
		textArea.setEditable(false);
		textArea.setId("textArea");
		textArea.getStylesheets().add(stylesheet);
		
		/* layout -> bottom */
		Button ok = new Button("OK");
		ok.setPrefWidth(60);
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				close();
			}
		});
		
		/* add items to the layout */
		layout.setCenter(textArea);
		layout.setBottom(ok);
		
		BorderPane.setAlignment(ok, Pos.CENTER);
		BorderPane.setMargin(textArea, new Insets(20, 10, 10, 10));
		BorderPane.setMargin(ok, new Insets(10, 10, 20, 10));
		
		/* create scene */
		Scene scene = new Scene(layout, width, height);
		
		/* set stage preferences */
		this.setScene(scene);
		this.setTitle(title);
		this.setResizable(false);
//		if (icon != null) {
//			this.getIcons().add(icon);
//		}
//		
		/* un-comment line below if you want dialog to be always on top */
		this.initModality(Modality.APPLICATION_MODAL);
	}
}