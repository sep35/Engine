package popup;

import gameManager.Team;
import gameManager.WinningCondition;
import gameObjectModel.GameObject;
import inputs.GameInfo;
import inputs.InfoUpdate;

import java.util.ArrayList;
import java.util.List;

import attributes.AbstractAttribute;
import attributes.Descriptor;
import attributes.ImageDescriptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import layout.AbstractLayout;

public class EditTeamForm extends Form {
	private final String NEUTRAL_SURVIVAL = "Survival Turns";
	private final String NEUTRAL_DESTRUCTION = "";
	public EditTeamForm(){
		super();
	}
	
	public void createForm(AbstractLayout layout, Team toEdit, List<Team> teamList){
		Stage newStage = new Stage();
		VBox winConditionVBox = new VBox();
		winConditionVBox.setStyle("-fx-background-color: Cyan");
		
		TextField teamName = new TextField(toEdit.getID());
		winConditionVBox.getChildren().add(teamName);
		
		Label winConditionLabel = new Label("Choose a winning condition type");
		HBox winChoices = new HBox();
		winChoices.setStyle("-fx-background-color: Cyan");
		
		ToggleGroup winConditionToggleGroup = new ToggleGroup();
		RadioButton justSurvival = new RadioButton("Just Survival");
		justSurvival.setToggleGroup(winConditionToggleGroup);
		
		RadioButton surviveAndDestroy = new RadioButton("Survive and Destroy");
		surviveAndDestroy.setToggleGroup(winConditionToggleGroup);
		
		RadioButton justDestroy = new RadioButton("Just Destroy");
		justDestroy.setToggleGroup(winConditionToggleGroup);
		winChoices.getChildren().addAll(justSurvival, surviveAndDestroy, justDestroy);
		winChoices.setAlignment(Pos.CENTER);
		winConditionVBox.getChildren().add(winChoices);
		
		HBox surviveParams = new HBox();
		TextField survivalTurns = new TextField();
		surviveParams.getChildren().addAll(new Label("Survival Turns"),survivalTurns);
		surviveParams.setAlignment(Pos.CENTER);
		winConditionVBox.getChildren().add(surviveParams);
		
		HBox destroy = new HBox();
		CheckBox destroyAll = new CheckBox("Destroy All?");
		ComboBox targets = new ComboBox();
		ObservableList<String> targetIDs = FXCollections.observableArrayList();
		for(Team t:teamList)
			targetIDs.add(t.getID());
		targets.setItems(targetIDs);
		//targets.setValue(targetIDs.get(0));
		destroy.getChildren().addAll(destroyAll, targets);
		destroy.setAlignment(Pos.CENTER);
		
		VBox attack = new VBox();
		TextField killPercent = new TextField("0");
		HBox kill = new HBox(new Label("Kill % (0-100)"), killPercent);
		kill.setAlignment(Pos.CENTER);
		attack.getChildren().addAll(destroy,kill);
		attack.setAlignment(Pos.CENTER);
		winConditionVBox.getChildren().add(attack);
		
		Button done = new Button("Done");
		done.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				IntegerStringConverter conv = new IntegerStringConverter();
				WinningCondition winning;
				int turns = Integer.MAX_VALUE;
				if(survivalTurns.getText().equals(null))
					turns = conv.fromString(survivalTurns.getText());
				String dAll = "or";
				if(destroyAll.isSelected())
					dAll = "and";
				if(winConditionToggleGroup.getSelectedToggle().equals(justSurvival))
					winning = new WinningCondition(turns,"or", "or");
				else if(winConditionToggleGroup.getSelectedToggle().equals(surviveAndDestroy))
					winning = new WinningCondition(turns,"and", dAll);
				else
					winning = new WinningCondition(turns,"or", dAll);
				if(!destroyAll.isSelected())
					winning.updateWinningCondition((Team)targets.getValue(), conv.fromString(killPercent.getText()));
				
				toEdit.setMyWinningCondition(winning);
				newStage.close();
				layout.setChangedAndNotify(new InfoUpdate((GameInfo)toEdit,"edit"));
			}
		});
		winConditionVBox.getChildren().add(done);
		winConditionVBox.setAlignment(Pos.CENTER);
		
		Scene teamWin = new Scene(winConditionVBox, 400,200);
		newStage.setScene(teamWin);
		newStage.show();
	}
}
