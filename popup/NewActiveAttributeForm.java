package popup;

import inputs.AbstractUpdate;
import inputs.InfoUpdate;
import inputs.MultiUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import attributes.ActiveAttribute;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import layout.AbstractLayout;

public class NewActiveAttributeForm extends Form{
	private MultiUpdate allUpdates;
	private ScrollPane myScrollPane;
	private FlowPane flowpane;
	private List<AbstractUpdate> attributes;
	public NewActiveAttributeForm(){
		super();
		myScrollPane = new ScrollPane();
		flowpane   = new FlowPane();
		attributes = new ArrayList<AbstractUpdate>();
	}

	public void createForm(AbstractLayout layout){
		Button addAttribute = new Button("Add another active attribute");
		addAttribute.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				addActiveAttribute();
			}
		});

		Button done = new Button("Done");
		done.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Iterator iter = flowpane.getChildren().iterator();
				while(iter.hasNext()){
					VBox current = (VBox) iter.next();	
					TextField name     = (TextField)((HBox) current.getChildren().get(0)).getChildren().get(0);
					TextField value    = (TextField)((HBox) current.getChildren().get(1)).getChildren().get(0);
					TextField subjects = (TextField)((HBox) current.getChildren().get(2)).getChildren().get(0);
					TextField enemy    = (TextField)((HBox) current.getChildren().get(3)).getChildren().get(0);
					TextField friend   = (TextField)((HBox) current.getChildren().get(4)).getChildren().get(0);
					attributes.add(new InfoUpdate(new ActiveAttribute(
							name.getText(),
							(double) Integer.parseInt(value.getText()),
							subjects.getText(),
							enemy.getText().equals("true"),
							friend.getText().equals("true"))
							, "add"));
				}
				allUpdates = new MultiUpdate(attributes);
				newStage.close();
				layout.setChangedAndNotify(allUpdates);
			}
		});

		addActiveAttribute();			
		Scene scene = new Scene(editables, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
		myScrollPane.setPrefSize(DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setContent(flowpane);
		myScrollPane.setStyle("-fx-background-color: Cyan");

		editables.getChildren().addAll(myScrollPane, addAttribute, done);
		editables.setStyle("-fx-background-color: Cyan");
		newStage.setScene(scene);
		newStage.setTitle("Active Attribute Creations");
		newStage.showAndWait();		
	}

	private void addActiveAttribute(){
		VBox allInputs = new VBox();
		HBox name 	  = new HBox();	
		HBox value    = new HBox();
		HBox subjects = new HBox();
		HBox enemy    = new HBox();
		HBox friends  = new HBox();
		HBox button = new HBox();

		TextField nameField    = new TextField("Give new attribute a name");
		nameField.setMinWidth(DEFAULT_STAGE_WIDTH/2);
		
		TextField valueField   = new TextField("Give value of attribute");
		valueField.setMinWidth(DEFAULT_STAGE_WIDTH/2);
		
		TextField subjectField = new TextField("Write other attribute this acts on");
		subjectField.setMinWidth(DEFAULT_STAGE_WIDTH/2);
		
		TextField enemyField   = new TextField("Pass in true if it affects enemies");
		enemyField.setMinWidth(DEFAULT_STAGE_WIDTH/2);
		
		TextField friendsField = new TextField("Pass in true if it affects friends");	
		friendsField.setMinWidth(DEFAULT_STAGE_WIDTH/2);

		Button remove = new Button("Remove Active Attribute");
		remove.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				removeAttribute(allInputs);
			}
		});

		name.getChildren().addAll(nameField);
		value.getChildren().addAll(valueField);
		subjects.getChildren().addAll(subjectField);
		enemy.getChildren().addAll(enemyField);
		friends.getChildren().addAll(friendsField);
		button.getChildren().addAll(remove);
		
		allInputs.getChildren().addAll(name, value, subjects, enemy, friends, button);
		allInputs.setStyle("-fx-background-color: Cyan");
		allInputs.setSpacing(10);

		flowpane.getChildren().addAll(allInputs);
		flowpane.setStyle("-fx-background-color: Cyan");
	}
	private void removeAttribute(VBox allinputs){
		flowpane.getChildren().remove(allinputs);
	}
}
