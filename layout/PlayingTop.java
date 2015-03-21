package layout;

import inputs.UndoRedoUpdate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class PlayingTop {
	private AbstractLayout myLayout;

	public PlayingTop(AbstractLayout layout) {
		myLayout = layout;
		Button undo = new Button("Undo");
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UndoRedoUpdate undoOrder = new UndoRedoUpdate(true);
				myLayout.setChangedAndNotify(undoOrder);
			}
		});

		Button redo = new Button("Redo");
		redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UndoRedoUpdate redoOrder = new UndoRedoUpdate(false);
				myLayout.setChangedAndNotify(redoOrder);
			}
		});

		Button play = new Button("Play");
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});
		
		ToolBar toolbar = new ToolBar();
		toolbar.getItems().addAll(new Separator(), undo, new Separator(), redo, new Separator(), play, new Separator());
		toolbar.setStyle("-fx-background-color: Deepskyblue");
		myLayout.getPane().setTop(toolbar);
		myLayout.getPane().setStyle("-fx-background-color: Deepskyblue");
	}
}
