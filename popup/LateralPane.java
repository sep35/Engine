package popup;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import gameObjectModel.AbstractRange;
import gameObjectModel.LateralRange;

public class LateralPane extends InteractionPane {
	private CheckBox canJump;
	private TextField horizontalText, verticalText, diagonalText;
	
	public LateralPane(){
		GridPane interactions = new GridPane();
		canJump = new CheckBox("Can Jump");
		Label horizontal = new Label("Horizontal Range");
		horizontalText = new TextField();
		Label vertical = new Label("Vertical Range");
		verticalText = new TextField();
		Label diagonal = new Label("Diagonal Range");
		diagonalText = new TextField();
		interactions.add(horizontal, 0, 0);
		interactions.add(vertical, 0, 1);
		interactions.add(diagonal, 0, 2);
		interactions.add(horizontalText, 1, 0);
		interactions.add(verticalText, 1, 1);
		interactions.add(diagonalText, 1, 2);
		interactions.add(canJump, 0, 3);
		this.getChildren().add(interactions);
	}
	@Override
	public AbstractRange getRange() {
		Integer horizontalMovement = Integer.parseInt(horizontalText.getText());
		Integer verticalMovement = Integer.parseInt(verticalText.getText());
		Integer diagonalMovement = Integer.parseInt(diagonalText.getText());
		Map<String, Integer> directions = new HashMap<String, Integer>();
		directions.put("horizontal", horizontalMovement);
		directions.put("vertical", verticalMovement);
		directions.put("diagonal", diagonalMovement);
		
		return new LateralRange(directions, canJump.isSelected(), "motion");
	}

}
