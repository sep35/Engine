package layout;

import java.util.ResourceBundle;

import javafx.geometry.Orientation;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class PlayingLeft{
	private AbstractLayout myLayout;
    private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";
	
	public PlayingLeft(AbstractLayout layout){
		
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		int RIGHT_WIDTH = Integer.parseInt(myConstants.getString("Right_Pane_Width"));
		int RIGHT_HEIGHT = Integer.parseInt(myConstants.getString("Right_Pane_Height"));
		
	    myLayout = layout;
		SplitPane myPane = new SplitPane();
		myPane.setPrefSize(RIGHT_WIDTH, RIGHT_HEIGHT);
		myLayout.getPane().setLeft(myPane);
	}
}
