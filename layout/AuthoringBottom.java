package layout;

import inputs.LevelUpdate;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AuthoringBottom extends Pane {
	private AbstractLayout myLayout;

	public AuthoringBottom(AbstractLayout layout) {
		myLayout = layout;
		myLayout.getPane().setBottom(this);
		FlowPane flow = new FlowPane();
		Button butt = new Button("Create New Level");
		butt.setOnAction(e -> myLayout.setChangedAndNotify(new LevelUpdate(
				"add")));
		butt.setStyle("-fx-background-color: Deepskyblue");
		Button prev = new Button("Previous Level");
		prev.setOnAction(e -> myLayout.setChangedAndNotify(new LevelUpdate(
				"back")));
		prev.setStyle("-fx-background-color: Deepskyblue");
		Button next = new Button("Next Level");
		next.setOnAction(e -> myLayout.setChangedAndNotify(new LevelUpdate(
				"forward")));		
		next.setStyle("-fx-background-color: Deepskyblue");
		
		ToolBar toolbar = new ToolBar();
		toolbar.getItems().addAll(new Separator(), butt, new Separator(), prev, new Separator(), next, new Separator());
		toolbar.setStyle("-fx-background-color: Deepskyblue");
		getChildren().add(toolbar);

	}
}
