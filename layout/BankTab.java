package layout;

import gameManager.Team;
import gameObjectModel.GameObject;
import inputs.GameInfo;
import inputs.InfoUpdate;
import inputs.ObjectUpdate;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import popup.EditTeamForm;
import util.InfoFormatter;
import util.Reflection;
import view.TeamPrompt;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.Descriptor;
import attributes.PassiveAttribute;
import boardObjectModels.InfoBank;

public class BankTab extends Tab {
	private AbstractLayout layout;
	private String myName;
	private FlowPane myFlowPane;
	private ScrollPane myScrollPane;
	private int IMG_W = 80;
	private int IMG_H = 80;
	private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";

	public BankTab(String name, AbstractLayout backend){
		layout = backend;
		myName = name;
		createBank();
	}

	private void createBank(){
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		int LEFT_WIDTH = Integer.parseInt(myConstants.getString("Left_Pane_Width"));
		int LEFT_HEIGHT = Integer.parseInt(myConstants.getString("Left_Pane_Height"));	
		myFlowPane = new FlowPane();
		myScrollPane = new ScrollPane();
		setContent(myScrollPane);
		myFlowPane.setStyle("-fx-background-color: Lightblue");
		myFlowPane.setPrefWrapLength(LEFT_WIDTH);
		myFlowPane.setVgap(5);
		myFlowPane.setHgap(5);
		myScrollPane.setPrefSize(LEFT_WIDTH, LEFT_HEIGHT);
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setContent(myFlowPane);
		myScrollPane.setStyle("-fx-background-color: Deepskyblue");
		this.setText(myName);
		this.setContent(myScrollPane);
	}

	public Tab getTab(){
		return this;
	}

	public void clear(){
		myFlowPane.getChildren().clear();
	}


	public void addImage(ImageView img){
		img.setFitHeight(IMG_H);
		img.setFitWidth(IMG_W);
		myFlowPane.getChildren().add(img);
	}

	public void update(InfoBank b){
		myFlowPane.getChildren().clear();
		String methodName = Introspector.decapitalize(myName);
		Reflection.callMethod(this, methodName, b);
	}

	private void passiveAttribute(InfoBank b){
		for(GameInfo obj : b.getList()){
			PassiveAttribute passAtt = (PassiveAttribute) obj;
			Label name = new Label(passAtt.getName());
			myFlowPane.getChildren().add(name);
		}

	}
	private void activeAttribute(InfoBank b){
		for(GameInfo obj : b.getList()){		
			ActiveAttribute activAtt = (ActiveAttribute) obj;
			Label name = new Label(activAtt.getName());
			myFlowPane.getChildren().add(name);
		}
	}
		private void imageDescriptor(InfoBank b){
		for(GameInfo obj : b.getList()){
			Descriptor imaAtt = (Descriptor) obj;
			//System.out.println(imaAtt.getName());
			ImageView image = new ImageView(new Image(imaAtt.getValue()));			
			myFlowPane.getChildren().add(image);
			
		}
	}
	 

	private void gameObject(InfoBank b){
		GridPane attributes = new GridPane();
		attributes.setHgap(10);
		attributes.setVgap(5);
		int counter = 0;
		Map<Label, ImageView> objectMap = new HashMap<Label, ImageView>();
		for(GameInfo obj : b.getList()){
		    StringBuilder s = new StringBuilder();
			GameObject gameAtt = (GameObject) obj;
			ImageView image = new ImageView();
			
			s.append("Object:\n");
			for(Descriptor d:gameAtt.getDescriptors()){
			    if(d.getName().equals("Image")) image.setImage(new Image(d.getValue()));
			    else s.append(InfoFormatter.formatDescriptor(d.getName(), d.getValue()));
			}
			s.append(" - Attributes:\n");
			for(AbstractAttribute a: gameAtt.getAttributes()){
			    if(a instanceof ActiveAttribute){
			        ActiveAttribute actA = (ActiveAttribute) a;
			          s.append(InfoFormatter.formatActiveAttribute(actA.getName(), actA.getReceiver(), actA.getValue()));
			    }
			    if(a.getClass().getSimpleName().equals("PassiveAttribute")){
			        PassiveAttribute passA = (PassiveAttribute) a;
			        s.append(InfoFormatter.formatPassiveAttribute(passA.getName(), passA.getValue()));
                }
			}
			objectMap.put(new Label(s.toString()), image);
		}
		int indexCounter=0;
		for(Label lab:objectMap.keySet()){
		    final int index = indexCounter;
		    attributes.add(lab, 0, counter);
		    if(!(objectMap.get(lab)==null)){
		        ImageView im = objectMap.get(lab);
		        attributes.add(im, 1, counter);
		        addObjectDragListener(im, layout, attributes, b, indexCounter);
		    }
		    Button delete = new Button("Delete");
		    attributes.add(delete, 1, counter+1);
		    delete.setOnAction(e->layout.setChangedAndNotify(new InfoUpdate(b.getList().get(index), "remove")));
		    counter+=2;
		    indexCounter++;
		}
		myFlowPane.getChildren().add(attributes);
	}
	
	
	
	private void team(InfoBank b){
		List<Team> teams = new ArrayList<Team>();
		for(GameInfo obj : b.getList()){
			Team gameTeam = (Team) obj;
			List<Team> others = new ArrayList<Team>(teams);
			teams.add(gameTeam);
			Button name = new Button(gameTeam.getID());
			name.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					EditTeamForm form = new EditTeamForm();
					form.createForm(layout, gameTeam, others);
				}
			});
            myFlowPane.getChildren().add(name);
			
		}
	}
	public String getName(){
		return myName;
	}
	
	private void addObjectDragListener (ImageView p, AbstractLayout layout, GridPane grid, InfoBank b, int i) {
	    int x = GridPane.getColumnIndex(p);
        int y = GridPane.getRowIndex(p);
        final Delta dragDelta = new Delta();
        GameObject gameOb = (GameObject) b.getList().get(i);
        p.setOnMousePressed(new EventHandler<MouseEvent>() {
           

            @Override
            public void handle (MouseEvent mouseEvent) {
                ImageView dragItem = new ImageView(p.getImage());
                grid.getChildren().remove(p);
                p.setFitHeight(20);
                p.setFitWidth(20);
                grid.add(dragItem, x, y);
                p.setLayoutX(mouseEvent.getSceneX()-10);
                p.setLayoutY(mouseEvent.getSceneY()-90);
                addObjectDragListener(dragItem, layout, grid, b, i);
                layout.getPane().getChildren().add(p);
                dragDelta.x = p.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = p.getLayoutY() - mouseEvent.getSceneY();
                p.setCursor(Cursor.MOVE);
            }
        });
        
        p.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent arg0) {
                layout.getPane().getChildren().remove(p);
                p.setLayoutX(arg0.getSceneX() + dragDelta.x);
                p.setLayoutY(arg0.getSceneY() + dragDelta.y);
               // int xDim = layout.getBoard().getPatchList().size();
               // int yDim = layout.getBoard().getPatchList().get(0).size();
                double screenX = (arg0.getSceneX()-layout.getPane().getCenter().getLayoutX());
                double screenY = arg0.getSceneY()-layout.getPane().getCenter().getLayoutY()-75;
                
                int xDim = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Width")))/layout.getBoard().getPatchList().size());
                int yDim = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Height")))/layout.getBoard().getPatchList().get(0).size());
               // int x = (int) Math.floor(arg0.getX()/(xSize));
                //int y = (int) Math.floor(arg0.getY()/(ySize));
                
                
                
                
                int boardX = (int) Math.floor(screenX / xDim);
                int boardY = (int) Math.floor(screenY / yDim);
                ObjectUpdate temp = new ObjectUpdate();
                TeamPrompt team = new TeamPrompt();
                Descriptor teamID = null;
                GameObject g = gameOb;
                for(Descriptor d: g.getDescriptors()){
                    if(d.getName().equals("Team")){
                        d.setValue(team.getID());
                        teamID = d;
                    }
                    
                }
                if(teamID==null) g.getDescriptors().add(new Descriptor("Team", team.getID()));
                temp.setGameObject(g);
                
                temp.setCoordinates(Arrays.asList(boardX,boardY));
                if(boardX<xDim&&boardY<yDim&&boardX>=0&&boardY>=0){
                layout.setChangedAndNotify(temp);}
            }
        }); 

        p.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent mouseEvent) {
                p.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                p.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            }
        });
        p.setOnMouseEntered(event -> p.setCursor(Cursor.HAND));
    }
	
    class Delta {
        double x, y;
    }
}
