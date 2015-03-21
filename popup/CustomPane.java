package popup;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import gameObjectModel.AbstractRange;
import gameObjectModel.CustomRange;
import javafx.event.EventHandler;

public class CustomPane extends InteractionPane {
	private final Paint ON_COLOR = Color.LIMEGREEN;
	private final Paint OFF_COLOR = Color.LIGHTCORAL;
	private final Paint OBJ_COLOR = Color.GOLD;
	private CheckBox canJump;
	private GridPane rectGrid;
	private Rectangle[][] rectangles;
	private List<Integer> dimensions;

	public CustomPane(List<Integer> dims) {
		dimensions = dims;
		GridPane interactions = new GridPane();
		rectangles = new Rectangle[dimensions.get(0)][dimensions.get(1)];
		rectGrid = new GridPane();
		fillGrid();
		canJump = new CheckBox("Can Jump");
		interactions.add(rectGrid, 0, 0);
		interactions.add(canJump, 0, 1);
		this.getChildren().add(interactions);
	}

	private void fillGrid() {
		for (int i = 0; i < dimensions.get(0); i++) {
			for (int j = 0; j < dimensions.get(1); j++) {
				Rectangle rect = new Rectangle(10, 10);
				if (i == dimensions.get(0) / 2 && j == dimensions.get(1) / 2) {
					rect.setFill(OBJ_COLOR);
				} else {
					rect.setFill(OFF_COLOR);
					rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							if (rect.getFill().equals(OFF_COLOR))
								rect.setFill(ON_COLOR);
							else
								rect.setFill(OFF_COLOR);
						}
					});
				}
				rectGrid.add(rect, i, j);
				rectangles[i][j] = rect;
			}
		}
		rectGrid.setGridLinesVisible(true);
	}

	@Override
	public AbstractRange getRange() {
		List<List<Integer>> deltas = new ArrayList<List<Integer>>();
		for (int i = 0; i < rectangles.length; i++) {
			for (int j = 0; j < rectangles.length; j++) {
				if (rectangles[i][j].getFill().equals(ON_COLOR)) {
					ArrayList<Integer> relativePosition = new ArrayList<Integer>();
					relativePosition.add(new Integer(j - (dimensions.get(1) / 2)));
					relativePosition.add(new Integer(i - (dimensions.get(0) / 2)));
					deltas.add(relativePosition);
				}
			}
		}
		return new CustomRange(deltas, canJump.isSelected(), "motion");
	}
}
