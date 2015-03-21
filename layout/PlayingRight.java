package layout;

import java.util.List;
import java.util.ResourceBundle;

import gameObjectModel.GameObject;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import util.InfoFormatter;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.Descriptor;
import attributes.PassiveAttribute;
    
public class PlayingRight extends Pane{
    private AbstractLayout myLayout;
    private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";
    
	public PlayingRight(AbstractLayout layout){
		myLayout = layout;
		myLayout.getPane().setRight(this);
	}
	
	protected void updateItem(GameObject gameAtt, List<Integer> position){
	    getChildren().clear();
	    GridPane attributes = new GridPane();
	    
	    myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		int RIGHT_WIDTH = Integer.parseInt(myConstants.getString("Right_Pane_Width"));
		int RIGHT_HEIGHT = Integer.parseInt(myConstants.getString("Right_Pane_Height"));
		
		attributes.setPrefSize(RIGHT_WIDTH, RIGHT_HEIGHT);
	    attributes.setHgap(10);
	    attributes.setVgap(5);
	    StringBuilder s = new StringBuilder();
	    ImageView image = new ImageView();
	    FlowPane flow = new FlowPane(Orientation.VERTICAL);
	    if(gameAtt!=null){
	    s.append("Object:\n");
	    for(Descriptor d:gameAtt.getDescriptors()){
	        if(d.getName().equals("Image")) image.setImage(new Image(d.getValue()));
	        else flow.getChildren().add(new Label(
	                InfoFormatter.formatDescriptor(d.getName(), d.getValue())));
	    }
	    s.append(" - Attributes:\n");
	    for(AbstractAttribute a: gameAtt.getAttributes()){
	        if(a instanceof ActiveAttribute){
	            ActiveAttribute actA = (ActiveAttribute) a;
	            Button b = new Button(InfoFormatter.formatActiveAttribute(actA.getName(), 
	                            actA.getReceiver(), actA.getValue()));
	            flow.getChildren().add(b);
	            b.setOnAction(e->myLayout.sendBoardInfo(actA.getName(), position));
	        }
	        if(a.getClass().getSimpleName().equals("PassiveAttribute")){
	            PassiveAttribute passA = (PassiveAttribute) a;
	            flow.getChildren().add(new Label(
	                    InfoFormatter.formatPassiveAttribute(passA.getName(), passA.getValue())));
	        }
	    }
	    Button move = new Button("move");
	    move.setOnAction(e->myLayout.sendBoardInfo(move.getText(), position));
	    flow.getChildren().add(move);
	    }
	    
	    attributes.add(flow, 0, 0);
	    attributes.add(image, 1, 0);
	    getChildren().add(attributes);
	    
	}

}
