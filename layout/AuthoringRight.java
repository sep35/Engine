package layout;

import gameObjectModel.GameObject;

import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.InfoFormatter;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.Descriptor;
import attributes.PassiveAttribute;
    
public class AuthoringRight extends Pane{
    private AbstractLayout myLayout;
    private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";
	private FlowPane myFlow;
	private Pane myPane;
    
	public AuthoringRight(AbstractLayout layout){
	    myFlow = new FlowPane(Orientation.VERTICAL);
	    myPane = new Pane();
	    
		myLayout = layout;
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		int RIGHT_WIDTH = Integer.parseInt(myConstants.getString("Right_Pane_Width"));
        int RIGHT_HEIGHT = Integer.parseInt(myConstants.getString("Right_Pane_Height"));
        myFlow.setPrefSize(RIGHT_WIDTH, RIGHT_HEIGHT);
        myPane.setPrefSize(RIGHT_WIDTH, RIGHT_HEIGHT);
        myPane.setStyle("-fx-background-color: white");
        myFlow.getChildren().add(myPane);
        this.getChildren().add(myFlow);
        myLayout.getPane().setRight(this);
	}
	
	protected void updateItem(GameObject gameAtt, List<Integer> position){
	    myPane.getChildren().clear();
	    GridPane attributes = new GridPane();
	    
	   

	    attributes.setHgap(10);
	    attributes.setVgap(5);
	    StringBuilder s = new StringBuilder();
	    ImageView image = new ImageView();
	    FlowPane flow = new FlowPane();
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
	            //b.setOnAction(e->myLayout.sendBoardInfo(actA.getName(), position));
	        }
	        if(a.getClass().getSimpleName().equals("PassiveAttribute")){
	            PassiveAttribute passA = (PassiveAttribute) a;
	            Label temp  = new Label(
                        InfoFormatter.formatPassiveAttribute(passA.getName(), passA.getValue()));
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
	    myPane.getChildren().add(attributes);
	    
	}

}

