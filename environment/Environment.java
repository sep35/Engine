package environment;

import java.util.Arrays;

import serialization.*;
import layout.*;
import boardObjectModels.*;

public class Environment extends AbstractEnvironment {
	protected boolean isAuthoring;
	protected AbstractLayout playing, authoring;
	
	public Environment(String typeOfEnvironment, int x, int y, int moves){
		myBoardHolder = new ObservableBoardHolder(new Board(Arrays.asList(x, y)), moves);
		myDataHandler = new DataHandler(myBoardHolder);
		playing = new PlayingLayout(myBoardHolder);
		authoring = new AuthoringLayout(myBoardHolder);
		playing.addObserver(myDataHandler);
		authoring.addObserver(myDataHandler);
		isAuthoring = typeOfEnvironment.equals("Authoring");
		myBoardHolder.addObserver(authoring);
		switchEnvironment();
	}
	public void switchEnvironment(){
		myBoardHolder.deleteObservers();
		if(isAuthoring)	 myGameView = authoring;
		if(!isAuthoring) myGameView = playing;
		myBoardHolder.addObserver(myGameView);
		myBoardHolder.renderBoard();
		myGameView.updateBoard(myBoardHolder);
		isAuthoring = !isAuthoring;		
	}
	public LayoutPane getPane(){
		return myGameView.getPane();
	}
	public Boolean getScene(){
		return isAuthoring;
	}
}
