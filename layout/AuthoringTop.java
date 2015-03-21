package layout;

import inputs.InfoUpdate;
import inputs.ObjectUpdate;
import inputs.UndoRedoUpdate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import popup.NewActiveAttributeForm;
import popup.NewObjectForm;
import popup.NewPassiveAttributeForm;
import popup.NewTeamForm;
import view.DialogBox;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import attributes.ImageDescriptor;

public class AuthoringTop{
	private AbstractLayout myLayout;
	private ObjectUpdate newIO;

	public AuthoringTop(AbstractLayout layout){
		myLayout = layout;

		Button newTeam = new Button("New Team");
		newTeam.setStyle("-fx-background-color: Deepskyblue");
		newTeam.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				NewTeamForm newTeamForm = new NewTeamForm();
				newTeamForm.createForm(myLayout);
			}
		});
		Button newObject = new Button("New Object");
		newObject.setStyle("-fx-background-color: Deepskyblue");
		newObject.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				createObject();
			}
		});
		
		Button addPassiveAttribute = new Button("Add Passive Attribute");
		addPassiveAttribute.setStyle("-fx-background-color: Deepskyblue");
		addPassiveAttribute.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				NewPassiveAttributeForm newPassiveAttribute = new NewPassiveAttributeForm();
				newPassiveAttribute.createForm(myLayout);
			}
		});
		
		Button addActiveAttribute = new Button("Add Active Attribute");
		addActiveAttribute.setStyle("-fx-background-color: Deepskyblue");
		addActiveAttribute.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				NewActiveAttributeForm newActiveAttribute = new NewActiveAttributeForm();
				newActiveAttribute.createForm(myLayout);
			}
		});
		
		Button addImage = new Button("Add Image");
		addImage.setStyle("-fx-background-color: Deepskyblue");
		addImage.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"));
				fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
				File file = fileChooser.showOpenDialog(new Stage());
				if(file!=null){               
					try {
						BufferedImage bufferedImage = ImageIO.read(file);
						Image image = SwingFXUtils.toFXImage(bufferedImage, null);
						myLayout.setChangedAndNotify(new InfoUpdate(new ImageDescriptor("imagedescriptor", "images/"+file.getName()), "add"));
						
					} catch (IOException ex) {

					}
				}

			}
		});

		Button undo = new Button("Undo");
		undo.setStyle("-fx-background-color: Deepskyblue");
		undo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				UndoRedoUpdate undoOrder = new UndoRedoUpdate(true);
				myLayout.setChangedAndNotify(undoOrder);
			}
		});

		Button redo = new Button("Redo");
		redo.setStyle("-fx-background-color: Deepskyblue");
		redo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				UndoRedoUpdate redoOrder = new UndoRedoUpdate(false);
				myLayout.setChangedAndNotify(redoOrder);
			}
		});

		Button play = new Button("Play");
		play.setStyle("-fx-background-color: Deepskyblue");
		play.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){

			}
		});

		Button clearBoard = new Button("Clear Board");
		clearBoard.setStyle("-fx-background-color: Deepskyblue");
		clearBoard.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				
			}
		});

		Button conditions = new Button("Edit Game Conditions");
		conditions.setStyle("-fx-background-color: Deepskyblue");
		conditions.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				DialogBox dialogBox = new DialogBox();
			}
		});

		ToolBar toolBar1 = new ToolBar();
		toolBar1.getItems().addAll(
				new Separator(),
				newTeam,
				newObject,
				new Separator(),
				new Separator(),
				addPassiveAttribute,
				addActiveAttribute,
				addImage,
				clearBoard,
				new Separator(),
				new Separator(),
				undo,
				redo,
				play,
				new Separator(),
				new Separator(),
				conditions,
				new Separator()
				);
		toolBar1.setStyle("-fx-background-color: Deepskyblue");
		myLayout.getPane().setTop(toolBar1);
		myLayout.getPane().setStyle("-fx-background-color: Deepskyblue");
	}
	
	private void createObject(){
		NewObjectForm newObject = new NewObjectForm();
		newObject.createForm(myLayout);
	}
}