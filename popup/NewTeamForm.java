package popup;

import inputs.InfoUpdate;
import gameManager.Team;
import gameManager.WinningCondition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import layout.AbstractLayout;

public class NewTeamForm extends Form {
//	private final String NEUTRAL_SURVIVAL = "Survival Turns";
//	private final String NEUTRAL_DESTRUCTION = "";
	public NewTeamForm(){
		super();
	}
	
	public void createForm(AbstractLayout myLayout){
		TextField teamName = new TextField("Team Name");
		Button create = new Button("Create Team");
		create.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				Team toSend = new Team(teamName.getText(), new WinningCondition());
				newStage.close();
				myLayout.setChangedAndNotify(new InfoUpdate(toSend, "add"));
			}
		});
		editables.getChildren().addAll(teamName, create);
		editables.setStyle("-fx-background-color: Cyan");
		Scene newObj = new Scene(editables,100,100);
		newStage.setScene(newObj);
		newStage.showAndWait();
//		VBox winConditionVBox = new VBox();
//		Label winConditionLabel = new Label("Choose a winning condition type");
//		ToggleGroup winConditionToggleGroup = new ToggleGroup();
//		RadioButton justSurvival = new RadioButton("Just Survival");
//		RadioButton surviveAndDestroy = new RadioButton("Survive and Destroy");
//		RadioButton justDestroy = new RadioButton("Just Destroy");
//		
//		HBox winAmounts = new HBox();
		
	}
}
