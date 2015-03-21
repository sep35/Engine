package popup;

import java.util.ResourceBundle;

import inputs.AbstractUpdate;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Form {
	
	private final Insets FORM_PADDING = new Insets(5, 5, 5, 5);
	private final double EDITABLES_SPACING = 5;
	protected Stage newStage;
	protected VBox editables;
	protected GridPane gridPane;
	private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";
	protected int DEFAULT_STAGE_WIDTH;
	protected int DEFAULT_STAGE_HEIGHT;
	
	public Form(){
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		DEFAULT_STAGE_WIDTH = Integer.parseInt(myConstants.getString("Default_StageW"));
		DEFAULT_STAGE_HEIGHT = Integer.parseInt(myConstants.getString("Default_StageH"));
		newStage = new Stage();
		editables = new VBox();
		editables.setPadding(FORM_PADDING);
		editables.setSpacing(EDITABLES_SPACING);
		gridPane = new GridPane();
	}
}
