package popup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import attributes.PassiveAttribute;
import inputs.AbstractUpdate;
import inputs.InfoUpdate;
import inputs.MultiUpdate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import layout.AbstractLayout;

public class NewPassiveAttributeForm extends Form {
	private MultiUpdate toAdd;
	private List<AbstractUpdate> attributes;
	private int attributeNumber;
	private VBox attributeVBox;
	
	public NewPassiveAttributeForm(){
		super();
		attributes = new ArrayList<AbstractUpdate>();
		attributeNumber = 1;
	}
	
	public void createForm(AbstractLayout myLayout){
		attributeVBox = new VBox();
		attributeVBox.setStyle("-fx-background-color: Cyan");
		Button newAttribute = new Button("Add another Passive Attribute");
		newAttribute.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				addAttributeSelection();
			}
		});
		
		addAttributeSelection();
		Button finish = new Button("Finish");
		finish.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				Iterator iter = attributeVBox.getChildren().iterator();
				while(iter.hasNext()){
					HBox current = (HBox) iter.next();
					TextField name = (TextField) current.getChildren().get(0);
					attributes.add(new InfoUpdate(new PassiveAttribute(name.getText(), 0.0), "add"));
				}
				toAdd = new MultiUpdate(attributes);
				newStage.close();
				myLayout.setChangedAndNotify(toAdd);
			}
		});
		editables.getChildren().addAll(attributeVBox, newAttribute, finish);
		editables.setStyle("-fx-background-color: Cyan");
		newAttribute.setStyle("-fx-background-color: Cyan");
		finish.setStyle("-fx-background-color: Cyan");
		Scene newObj = new Scene(editables,300,500);
		newStage.setScene(newObj);
		newStage.showAndWait();
	}
	
	private void addAttributeSelection(){
		HBox attributeAndRemove = new HBox();
		TextField nameField = new TextField("Passive Attribute " + attributeNumber + " Name");
		Button remove = new Button("Remove");
		remove.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				removeAttribute(attributeAndRemove);
			}
		});
		attributeAndRemove.setStyle("-fx-background-color: Cyan");
		attributeAndRemove.getChildren().addAll(nameField, remove);
		attributeNumber++;
		attributeVBox.getChildren().add(attributeAndRemove);
	}

	protected void removeAttribute(HBox attributeAndRemove) {
		attributeVBox.getChildren().remove(attributeAndRemove);
	}
}
