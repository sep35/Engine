package layout;

import gameManager.Victory;
import gameObjectModel.GameObject;
import inputs.LevelUpdate;

import java.util.List;
import java.util.Observable;

import javafx.scene.image.ImageView;
import view.WinningPrompt;
import boardObjectModels.ObservableBoardHolder;
import boardObjectModels.Output;

public class PlayingLayout extends AbstractLayout{
	public PlayingLayout(ObservableBoardHolder board){
		updateBoard(board);
	    myLevelManager = new LevelManager(this);
		myPane = new LayoutPane(this);
		new PlayingTop(this);
		new PlayingCenter(this);
	}

	@Override
	void updateImages(ImageView imageView) {
		
	}
	
	
//	protected void updateViewLeft(GameObject obj){
//	    
//	}
	
	 @Override
	    public void update (Observable o, Object arg) {
	        // TODO Render board for view by user
	     Output out = (Output) arg;
	        List<String> changedList = out.getElements();
	        Victory v = out.getVictory();
	        myLevelManager.update(v);
	        AbstractCenter p = (AbstractCenter) myPane.getCenter();
	        p.update();
	        
	    }

    @Override
    void updateViewRight (GameObject g, List<Integer> position) {
        PlayingRight right = (PlayingRight) myPane.getRight();
	    right.updateItem(g, position);
    }

    @Override
    void sendBoardInfo (String s, List<Integer> position) {
        PlayingCenter center = (PlayingCenter) myPane.getCenter();
        center.updateItem(s, position);
    }

    @Override
    void setWinningTeam (String s) {
       WinningPrompt win = new WinningPrompt(s);
        setChangedAndNotify(new LevelUpdate("forward"));
    }
}
