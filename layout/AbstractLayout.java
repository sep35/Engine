package layout;

import gameObjectModel.GameObject;
import inputs.AbstractUpdate;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.ImageView;
import boardObjectModels.Board;
import boardObjectModels.ObservableBoardHolder;

public abstract class AbstractLayout extends Observable implements Observer{
	protected LayoutPane myPane;
	protected ObservableBoardHolder myBoardHolder;
	protected LevelManager myLevelManager;
	protected String currTeam;
	
	public LayoutPane getPane(){
	    return myPane;
	}
	public String getTeam(){
	    return currTeam;
	}
	
	public void updateBoard(ObservableBoardHolder board){
	    myBoardHolder = board;
	}
	public Board getBoard(){
	    return myBoardHolder.getBoard();
	}
	public ObservableBoardHolder getHolder(){
		return myBoardHolder;
	}

    @Override
    public void update (Observable o, Object arg) {
        // TODO Render board for view by user
        //List<String> changedList = (List<String>) arg;
        //AbstractCenter p = (AbstractCenter) myPane.getCenter();
        //p.update();
    }
    
    public void setChangedAndNotify(AbstractUpdate in){
        setChanged();
        notifyObservers(in);
    }

	abstract void updateImages(ImageView imageView);
	abstract void updateViewRight(GameObject g, List<Integer> position);
	abstract void sendBoardInfo(String s, List<Integer> position);
	abstract void setWinningTeam(String s);

}
