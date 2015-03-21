package popup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Tester extends Application {
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception{
		Label o = new Label("1");
		Label i = new Label("2");
		Label j = new Label("3");
		Label k = new Label("4");
		
		GridPane m = new GridPane();
		m.add(o, 0, 0);
		m.add(i, 1, 0);
		m.add(j, 0, 1);
		m.add(k, 1, 1);
		Scene s = new Scene(m, 500, 500);
		arg0.setScene(s);
		arg0.show();
		
		for(int l = 1; l  <m.getChildren().size(); l = l*2+1){
			System.out.println(m.getChildren().get(l).toString());
		}	
	}

}
