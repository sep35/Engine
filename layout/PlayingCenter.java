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

import sun.security.tools.policytool.Resources;
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

public class PlayingCenter extends AbstractCenter {

    private VisualBoardManager myBoardManager;
    private int myClickCounter;
    private Patch myClickedPatch;
    private ActiveAttribute myActiveAttribute;
    private List<Integer> myDims;
    private int xSize, ySize;

    public PlayingCenter(AbstractLayout layout) {
        super(layout);
        
        myDims = layout.getBoard().getDims();
        xSize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Width"))))/myDims.get(0);
        ySize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Height"))))/myDims.get(1);
        
        myClickCounter = 0;
        myClickedPatch = new Patch();
        myBoardManager = new VisualBoardManager(layout);
        addBoardMouseListener();
    }

    protected void updateItem(String s, List<Integer> position){
        if(s.equals("move")) addMotionRange(position);
        else addInteractRange(s, position);
    }

    private void addMotionRange(List<Integer> position){
        if(myClickCounter == 0){ 
            myClickedPatch = myLayout.getBoard().getPatchAt(position);
            List<Patch> availPatches = myBoardManager.getAvailablePatches(position,myLayout.getBoard());
            for (Patch p : availPatches) {
                List<Integer> myDims = p.getPosition();
                Rectangle r = new Rectangle(
                							xSize,
                							ySize,
                                        Color.WHITE);
                r.setOpacity(0.5);
                myVisualBoard.add(r, myDims.get(0), myDims.get(1));
            }
            if (myClickedPatch.getGameObject() != null) {
                myClickCounter = 1;
            }
        }
    }

    private void addInteractRange(String s, List<Integer> position){
        if(myClickCounter == 0){ 
            myClickedPatch = myLayout.getBoard().getPatchAt(position);
            GameObject g = myLayout.getBoard().getGameObjectAt(position);
            for(AbstractAttribute abs: g.getAttributes()){
                if(s.equals(abs.getName())) myActiveAttribute = (ActiveAttribute) abs;
            }
            List<Patch> availPatches = myBoardManager.getAttackablePatches(
                    position, myLayout.getBoard());
            for (Patch p : availPatches) {
                List<Integer> myDims = p.getPosition();
                Rectangle r = new Rectangle(xSize, ySize, Color.RED);
                r.setOpacity(0.3);
                myVisualBoard.add(r, myDims.get(0), myDims.get(1));
            }
            if (myClickedPatch.getGameObject() != null) {
                myClickCounter = 2;
            }
        }
    }

    private void addBoardMouseListener() {
        myVisualBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                xSize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Width")))/myBoard.getPatchList().size());
                ySize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Height")))/myBoard.getPatchList().get(0).size());
                int x = (int) Math.floor(arg0.getX()/(xSize));
                int y = (int) Math.floor(arg0.getY()/(ySize));
                GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(x, y));
                if (myClickCounter == 0) {
                    update();
                    myLayout.updateViewRight(clicked, Arrays.asList(x,y));
                }
                else if (myClickCounter == 1) {
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
                        myLayout.updateViewRight(null, null);
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
                                myActiveAttribute,
                                myClickedPatch));
                        myLayout.updateViewRight(null, null);
                        myClickCounter = 0;
                    }
                }
            }
        });
    }
}
