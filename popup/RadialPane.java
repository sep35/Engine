package popup;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import gameObjectModel.AbstractRange;
import gameObjectModel.RadialRange;

public class RadialPane extends InteractionPane {

	private CheckBox canJump = new CheckBox("Can Jump");
	private TextField radiusText;
	public RadialPane(){
		GridPane interactions = new GridPane();
		canJump = new CheckBox("Can Jump");
		Label radius = new Label("Radial Range");
		radiusText = new TextField();
		interactions.add(radius, 0, 0);
		interactions.add(radiusText, 1, 0);
		interactions.add(canJump, 0, 1);
		this.getChildren().add(interactions);
	}
	@Override
	public AbstractRange getRange() {
		Integer radialMovement = Integer.parseInt(radiusText.getText());
		return new RadialRange(canJump.isSelected(), radialMovement, "motion");
	}

}
