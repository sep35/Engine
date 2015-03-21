package popup;

import gameObjectModel.GameObject;
import gameObjectModel.Motion;
import inputs.GameInfo;
import inputs.InfoUpdate;
import inputs.ObjectUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;
import layout.AbstractLayout;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.Descriptor;
import attributes.ImageDescriptor;
import attributes.PassiveAttribute;

public class NewObjectForm extends Form{
	private ObjectUpdate newIO;
	private InteractionPane movementTypeForm, lateralMovementForm, radialMovementForm, customMovementForm;
	private InteractionPane interactionTypeForm, lateralInteractionForm, radialInteractionForm, customInteractionForm;
	private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";

	public NewObjectForm(){
		super();
	}

	private void buildInteractionForms(List<Integer> dims) {
		lateralInteractionForm = new LateralPane();
		radialInteractionForm = new RadialPane();
		customInteractionForm = new CustomPane(dims);
	}

	private void buildMovementForms(List<Integer> dims) {
		lateralMovementForm = new LateralPane();
		radialMovementForm = new RadialPane();
		customMovementForm = new CustomPane(dims);
	}



	public void createForm(AbstractLayout myLayout){
		buildMovementForms(myLayout.getBoard().getDims());
		buildInteractionForms(myLayout.getBoard().getDims());
		newIO = new ObjectUpdate();
		interactionTypeForm = new InteractionPane();
		movementTypeForm = new InteractionPane();
		ScrollPane changeables = new ScrollPane();
		VBox passiveParameters = new VBox();
		TextField nameField = new TextField("Object Name");

		passiveParameters.getChildren().add(nameField);


		VBox movementVBox = new VBox();
		HBox movementTypes = new HBox();
		ToggleGroup movementType = new ToggleGroup();
		Label movementLabel = new Label("Select a movement type");
		RadioButton lateralMovement = new RadioButton("Lateral Movement");
		lateralMovement.setToggleGroup(movementType);
		RadioButton radialMovement = new RadioButton("Radial Movement");
		radialMovement.setToggleGroup(movementType);
		RadioButton customMovement = new RadioButton("Custom Movement");
		customMovement.setToggleGroup(movementType);
		movementTypes.getChildren().addAll(lateralMovement, radialMovement, customMovement);
		movementVBox.getChildren().addAll(movementLabel, movementTypes, movementTypeForm);


		lateralMovement.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				switchMovementForms(movementVBox, lateralMovementForm);
			}

		});
		radialMovement.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				switchMovementForms(movementVBox, radialMovementForm);
			}

		});
		customMovement.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				switchMovementForms(movementVBox, customMovementForm);
			}

		});

		VBox interactionVBox = new VBox();
		HBox interactionTypes = new HBox();
		ToggleGroup interactionType = new ToggleGroup();
		Label interactionLabel = new Label("Select an interaction type");
		RadioButton lateralInteraction = new RadioButton("Lateral Interaction");
		lateralInteraction.setToggleGroup(interactionType);
		RadioButton radialInteraction = new RadioButton("Radial Interaction");
		radialInteraction.setToggleGroup(interactionType);
		RadioButton customInteraction = new RadioButton("Custom Interaction");
		customInteraction.setToggleGroup(interactionType);

		lateralInteraction.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				switchInteractionForms(interactionVBox, lateralInteractionForm);
			}

		});
		radialInteraction.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				switchInteractionForms(interactionVBox, radialInteractionForm);
			}

		});
		customInteraction.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				switchInteractionForms(interactionVBox, customInteractionForm);
			}

		});

		interactionTypes.getChildren().addAll(lateralInteraction, radialInteraction, customInteraction);
		interactionVBox.getChildren().addAll(interactionLabel, interactionTypes, interactionTypeForm);

		editables.getChildren().add(passiveParameters);
		Separator sep1 = new Separator();
		sep1.setOrientation(Orientation.HORIZONTAL);
		editables.getChildren().add(sep1);
		editables.getChildren().add(movementVBox);
		Separator sep2 = new Separator();
		sep2.setOrientation(Orientation.HORIZONTAL);
		editables.getChildren().add(sep2);
		editables.getChildren().add(interactionVBox);
		Separator sep3 = new Separator();
		sep3.setOrientation(Orientation.HORIZONTAL);
		editables.getChildren().add(sep3);

		Label passLabel = new Label("Choose Living Attribute");
		editables.getChildren().add(passLabel);
		
		List<GameInfo> rawAttribs = new ArrayList<GameInfo>();
		rawAttribs.addAll(myLayout.getHolder().getMapping("PassiveAttribute").getList());
		List<PassiveAttribute> passiveAttribs = new ArrayList<PassiveAttribute>();
		for (GameInfo att:rawAttribs){
			passiveAttribs.add((PassiveAttribute)att);
		}
		
		ObservableList<String> attribNames = FXCollections.observableArrayList();
		for(PassiveAttribute pat : passiveAttribs){
			attribNames.add(pat.getName());
		}
		ListView<String> attribList = new ListView<String>();
		attribList.setItems(attribNames);
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		int VIEW_HEIGHT = Integer.parseInt(myConstants.getString("List_View_Height"));
		attribList.setPrefHeight(VIEW_HEIGHT);
		editables.getChildren().add(attribList);
		
		ScrollPane passiveAttributes = new ScrollPane();
		VBox pAttributes = new VBox();
		pAttributes.getChildren().add(new Label("Passive Attributes"));
		List<CheckBox> checkedPassive = new ArrayList<CheckBox>();
		for(int i = 0; i<passiveAttribs.size(); i++){
			CheckBox p_attributes = new CheckBox();
			p_attributes.setText(passiveAttribs.get(i).getName());
			checkedPassive.add(p_attributes);
			pAttributes.getChildren().add(p_attributes);
		}
		passiveAttributes.setContent(pAttributes);
		editables.getChildren().add(passiveAttributes);
		
		Separator sep5 = new Separator();
		sep5.setOrientation(Orientation.HORIZONTAL);
		editables.getChildren().add(sep5);
		
		List<GameInfo> rawAAttribs = new ArrayList<GameInfo>();
		rawAAttribs.addAll(myLayout.getHolder().getMapping("ActiveAttribute").getList());
		List<ActiveAttribute> activeAttribs = new ArrayList<ActiveAttribute>();
		for (GameInfo att:rawAAttribs){
			activeAttribs.add((ActiveAttribute)att);
		}
		ScrollPane activeAttributes = new ScrollPane();
		VBox aAttributes = new VBox();
		aAttributes.getChildren().add(new Label("Active Attributes"));
		List<CheckBox> checkedActive = new ArrayList<CheckBox>();
		for(int i = 0; i<activeAttribs.size(); i++){
			CheckBox a_attributes = new CheckBox();
			a_attributes.setText(activeAttribs.get(i).getName());
			checkedActive.add(a_attributes);
			aAttributes.getChildren().add(a_attributes);
		}
		activeAttributes.setContent(aAttributes);
		editables.getChildren().add(activeAttributes);

		Label imgLabel = new Label("Select Object Image");
		editables.getChildren().add(imgLabel);

		List<GameInfo> rawImgDescrip = new ArrayList<GameInfo>();
		rawImgDescrip.addAll(myLayout.getHolder().getMapping("ImageDescriptor").getList());
		List<ImageDescriptor> imgDescrips = new ArrayList<ImageDescriptor>();
		for (GameInfo id:rawImgDescrip)
			imgDescrips.add((ImageDescriptor)id);
		ObservableList<String> imgList = FXCollections.observableArrayList();
		for(ImageDescriptor id:imgDescrips){
			imgList.add(id.getValue());
		}
		ComboBox<String> imgChoose = new ComboBox<String>(imgList);
		editables.getChildren().add(imgChoose);

		ImageView imgSelected = new ImageView(new Image("images/duvall.jpg"));
		HBox imgDisplay = new HBox(imgSelected);
		imgDisplay.setAlignment(Pos.CENTER);
		editables.getChildren().add(imgDisplay);
		imgChoose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				imgDisplay.getChildren().remove(imgSelected);
				ImageView imgSelected = new ImageView(new Image(imgChoose.getValue()));
				imgDisplay.getChildren().add(imgSelected);
			}
		});

		
		/*
		 * Repeated code in interest of time will 
		 * be refactored in code masterpiece
		 * 
		 */
				
		
		Button done = new Button("Done");
		done.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				List<AbstractAttribute> attribs = new ArrayList<AbstractAttribute>();
				for(int i = 0; i<checkedActive.size(); i++){
					if(checkedActive.get(i).isSelected()){
						attribs.add(activeAttribs.get(i));
					}
				};
				
				for(int i = 0; i< checkedPassive.size(); i++){
					if(checkedPassive.get(i).isSelected()){
						attribs.add(passiveAttribs.get(i));
					}
				};		
			
				IntegerStringConverter conv = new IntegerStringConverter();
				List<Descriptor> descriptors = new ArrayList<Descriptor>();
				descriptors.add(new Descriptor("Name", nameField.getText()));
				descriptors.add(new ImageDescriptor("Image", imgChoose.getValue()));

				GameObject go = new GameObject(descriptors, attribs, 
						movementTypeForm.getRange(),
						interactionTypeForm.getRange(),
						passiveAttribs.get(attribList.getSelectionModel().getSelectedIndex()));
				newStage.close();
				myLayout.setChangedAndNotify(new InfoUpdate(go,"add"));
			}
		});
		editables.getChildren().add(done);
		changeables.setContent(editables);
		Scene newObj = new Scene(changeables,500,600);
		newStage.setScene(newObj);
		newStage.show();
	}

	private void switchMovementForms(VBox movementVBox,
			InteractionPane newMovementForm) {
		movementVBox.getChildren().remove(movementTypeForm);
		movementTypeForm = newMovementForm;
		movementVBox.getChildren().add(movementTypeForm);
	}

	private void switchInteractionForms(VBox interactionVBox, InteractionPane newInteractionForm){
		interactionVBox.getChildren().remove(interactionTypeForm);
		interactionTypeForm = newInteractionForm;
		interactionVBox.getChildren().add(interactionTypeForm);
	}

}
