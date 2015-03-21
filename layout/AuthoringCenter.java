package layout;

import gameObjectModel.GameObject;
import gameObjectModel.Motion;
import gameObjectModel.RadialRange;
import inputs.LevelUpdate;
import inputs.MoveUpdate;
import inputs.ObjectUpdate;
import inputs.RemoveUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.Descriptor;
import attributes.PassiveAttribute;

public class AuthoringCenter extends AbstractCenter {
	private Random rand;
	public AuthoringCenter(AbstractLayout layout) {	
	    super(layout);
	    rand = new Random();		
		addBoardMouseListener();
	}

	private void addBoardMouseListener () {
		myVisualBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle (MouseEvent arg0) { 
			xSize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Width")))/myBoard.getDims().get(0));
            ySize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Height")))/myBoard.getDims().get(1));
            int x = (int) Math.floor(arg0.getX()/(xSize));
            int y = (int) Math.floor(arg0.getY()/(ySize));
				if (arg0.isControlDown()) {
					Pane popup = new Pane();
					popup.setPrefSize(100, 100);
					popup.setStyle("-fx-background-color: white");
					popup.setLayoutX(arg0.getX());
					popup.setLayoutY(arg0.getY());
					GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(x, y));
					GridPane grid = new GridPane();
					int i = 0;
					if (clicked != null) {
						if (clicked.getAttributes() != null) {
							for (AbstractAttribute att : clicked.getAttributes()) {
								grid.add(new Label(att.getName()), 0, i);
								grid.add(new Label(att.getValue()+""), 1, i);
								i++;
							}
						}
					}
					FlowPane flow = new FlowPane(Orientation.VERTICAL);
					Button close = new Button("X");
					close.setOnAction(event -> myVisualBoardHolder.getChildren().remove(popup));
					Button edit = new Button("Edit");
					edit.setOnAction(new EventHandler<ActionEvent>(){

						@Override
						public void handle (ActionEvent arg0) {
							flow.getChildren().remove(edit);
							grid.getChildren().clear();
							int j = 0;
							Map<AbstractAttribute, TextField> attMap = new HashMap<AbstractAttribute, TextField>();
							if (clicked != null) {
								if (clicked.getAttributes() != null) {
									for (AbstractAttribute att : clicked.getAttributes()) {
										TextField text = new TextField(att.getValue()+"");
										attMap.put(att,text);
										grid.add(new Label(att.getName()), 0, j);
										grid.add(text, 1, j);
										j++;
									}
								}
							}
							Button save = new Button("Save");
							flow.getChildren().add(save);
							save.setOnAction(new EventHandler<ActionEvent>(){

								@Override
								public void handle (ActionEvent arg0) {
									int k=0;
									flow.getChildren().remove(save);
									grid.getChildren().clear();
									StringBuilder sb = new StringBuilder();
									if (clicked != null) {
										if (clicked.getAttributes() != null) {
											for (AbstractAttribute att : clicked.getAttributes()) {
												att.setValue(Double.parseDouble(attMap.get(att).getText()));
												grid.add(new Label(att.getName()), 0, k);
												grid.add(new Label(att.getValue()+""), 1, k);
												k++;
											}
										}
									}
									flow.getChildren().add(new Label(sb.toString())); 
									flow.getChildren().add(edit);
									myLayout.setChangedAndNotify(new ObjectUpdate(clicked, Arrays.asList(x,y)));
									
								}
							});
						}
					});
					flow.getChildren().add(close);
					flow.getChildren().add(grid);
					flow.getChildren().add(edit);
					popup.getChildren().add(flow);
//					myVisualBoardHolder.getChildren().add(popup);

				} else if(arg0.isAltDown()){
					GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(x, y));
					myLayout.setChangedAndNotify(new RemoveUpdate(clicked, Arrays.asList(x,y)));
				}

				else if(arg0.isShiftDown()){
					ObjectUpdate temp = new ObjectUpdate();
					Motion heroMotion = new Motion(true, true, false, false, 2);
					List<AbstractAttribute> heroAttributes = new ArrayList<AbstractAttribute>();
					heroAttributes.add(new PassiveAttribute("health", 500.));
					heroAttributes.add(new ActiveAttribute("attack", 50., "health", true, false));
					List<Descriptor> heroDescriptors = new ArrayList<Descriptor>();
                    heroDescriptors.add(new Descriptor("name", "Bill"));
                    heroDescriptors.add(new Descriptor("Team", "1"));
                    heroDescriptors.add(new Descriptor("Image", "images/duvall.jpg"));
					GameObject myHero = new GameObject(heroDescriptors, heroAttributes, 
							new RadialRange(true, 2, "motion"), new RadialRange(true, 4, "attack"), null);

					temp.setGameObject(myHero);
					temp.setCoordinates(Arrays.asList(x,y));
					System.out.println(x+" : "+y);
					myLayout.setChangedAndNotify(temp);
				}
				else{
				    myLayout.updateViewRight(myBoard.getGameObjectAt(Arrays.asList(x,y)), Arrays.asList(x,y));
				}
			}
		});

		final Mover dragMover = new Mover();
		myVisualBoard.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent mouseEvent) {
				// record a delta distance for the drag and drop operation.               
				dragMover.oldX = (int) Math.floor(mouseEvent.getX() / xSize);						
				dragMover.oldY = (int) Math.floor(mouseEvent.getY() / ySize);
			}
		});

		myVisualBoard.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent mouseEvent) {
				GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(dragMover.oldX, dragMover.oldY));
				dragMover.newX = (int) Math.floor(mouseEvent.getX() / xSize);
				dragMover.newY = (int) Math.floor(mouseEvent.getY() / ySize);
				myLayout.setChangedAndNotify(
						new MoveUpdate(clicked, Arrays.asList(dragMover.oldX, dragMover.oldY), 
								Arrays.asList(dragMover.newX, dragMover.newY)));
			}
		});
	}
	private void addBoardDragListener (Pane p) {
		final Delta dragDelta = new Delta();
		p.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent mouseEvent) {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = p.getLayoutX() - mouseEvent.getSceneX();
				dragDelta.y = p.getLayoutY() - mouseEvent.getSceneY();
				p.setCursor(Cursor.MOVE);
			}
		});
		p.setOnMouseReleased(event -> p.setCursor(Cursor.HAND));
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
	class Mover {
		int oldX, oldY, newX, newY;
	}
}
