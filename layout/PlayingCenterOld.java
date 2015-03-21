package layout;

import gameObjectModel.GameObject;
import gameObjectModel.LateralRange;
import gameObjectModel.Motion;
import gameObjectModel.RadialRange;
import inputs.InteractUpdate;
import inputs.MoveUpdate;
import inputs.ObjectUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.Descriptor;
import attributes.PassiveAttribute;
import boardObjectModels.Board;
import boardObjectModels.Patch;

public class PlayingCenterOld extends AbstractCenter {

    private VisualBoardManager myBoardManager;
    private int myClickCounter;
    private Patch myClickedPatch;

    public PlayingCenterOld(AbstractLayout layout) {
        super(layout);
        myClickCounter = 0;
        myClickedPatch = new Patch();
        myBoardManager = new VisualBoardManager();
        addBoardMouseListener();
    }

    private void addBoardMouseListener() {
        myVisualBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                int x = (int) Math.floor(arg0.getX()
                        / Integer.parseInt(myConstants
                                .getString("Board_Test_Size")));
                int y = (int) Math.floor(arg0.getY()
                        / Integer.parseInt(myConstants
                                .getString("Board_Test_Size")));
                if (arg0.isControlDown()) {
                    Pane popup = new Pane();
                    popup.setPrefSize(100, 100);
                    popup.setStyle("-fx-background-color: white");
                    popup.setLayoutX(arg0.getX());
                    popup.setLayoutY(arg0.getY());
                    GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(
                            x, y));
                    GridPane grid = new GridPane();
                    int i = 0;
                    if (clicked != null) {
                        if (clicked.getAttributes() != null) {
                            for (AbstractAttribute att : clicked
                                    .getAttributes()) {
                                grid.add(new Label(att.getName()), 0, i);
                                grid.add(new Label(att.getValue() + ""), 1, i);
                                i++;
                            }
                        }
                    }
                    FlowPane flow = new FlowPane(Orientation.VERTICAL);
                    Button close = new Button("X");
                    close.setOnAction(event -> myVisualBoardHolder
                            .getChildren().remove(popup));

                    flow.getChildren().add(close);
                    flow.getChildren().add(grid);
                    popup.getChildren().add(flow);
                    myVisualBoardHolder.getChildren().add(popup);
                }

                else {
                    if (myClickCounter == 0) {
                        update();
                        myClickedPatch = myLayout.getBoard().getPatchAt(
                                Arrays.asList(x, y));

                        if (arg0.isAltDown()) {
                            List<Patch> availPatches = myBoardManager
                                    .getAttackablePatches(Arrays.asList(x, y),
                                            myLayout.getBoard());
                            for (Patch p : availPatches) {
                                List<Integer> myDims = p.getPosition();
                                Rectangle r = new Rectangle(
                                                Integer.parseInt(myConstants
                                                        .getString("Rectangle_Test_Size")),
                                                Integer.parseInt(myConstants
                                                        .getString("Rectangle_Test_Size")),
                                                Color.RED);
                                r.setOpacity(0.3);
                                myVisualBoard.add(r, myDims.get(0), myDims.get(1));
                                if (myClickedPatch.getGameObject() != null) {
                                    myClickCounter = 2;
                                }
                            }
                        } else {
                            List<Patch> availPatches = myBoardManager
                                    .getAvailablePatches(Arrays.asList(x, y),
                                            myLayout.getBoard());
                            for (Patch p : availPatches) {
                                List<Integer> myDims = p.getPosition();
                                Rectangle r = new Rectangle(
                                                Integer.parseInt(myConstants
                                                        .getString("Rectangle_Test_Size")),
                                                Integer.parseInt(myConstants
                                                        .getString("Rectangle_Test_Size")),
                                                Color.WHITE);
                                r.setOpacity(0.5);
                                myVisualBoard.add(r, myDims.get(0), myDims.get(1));
                                if (myClickedPatch.getGameObject() != null) {
                                    myClickCounter = 1;
                                }
                            }
                        }

                    } else {
                        if (myClickCounter == 1) {
                            List<Patch> availPatches = myBoardManager
                                    .getAvailablePatches(
                                            myClickedPatch.getPosition(),
                                            myLayout.getBoard());
                            Patch clickedPatch = myLayout.getBoard()
                                    .getPatchAt(Arrays.asList(x, y));
                            if (availPatches.contains(clickedPatch)) {

                                myLayout.setChangedAndNotify(new MoveUpdate(
                                        myClickedPatch.getGameObject(),
                                        myClickedPatch.getPosition(), Arrays
                                                .asList(x, y)));
                                myClickCounter = 0;
                            }
                        } else {
                            List<Patch> attackPatches = myBoardManager
                                    .getAttackablePatches(
                                            myClickedPatch.getPosition(),
                                            myLayout.getBoard());
                            Patch clickedPatch = myLayout.getBoard()
                                    .getPatchAt(Arrays.asList(x, y));
                            if (attackPatches.contains(clickedPatch)) {

                                myLayout.setChangedAndNotify(new InteractUpdate(
                                        myBoard.getPatchAt(Arrays.asList(x, y)),
                                        myBoard.getPatchAt(Arrays.asList(x, y))
                                                .getGameObject()
                                                .getAttributes().get(1),
                                        myClickedPatch));

                                myClickCounter = 0;
                            }
                        }
                    }
                }
            }
        });

    }
}
