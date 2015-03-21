package popup;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

/**
 * Class to bring up popup for error messages
 * 
 * @author Tanaka Jimha
 *
 */

public class sPopup {
	public sPopup(String message){
		
			  final Popup popup=new Popup();
			  popup.setAutoHide(true);
			  popup.setX(300);
			  popup.setY(200);
			  popup.getContent().addAll(new Circle(25,25,50,Color.AQUAMARINE));
			  popup.getContent().add(new Label(message));
			  
			}
	}


