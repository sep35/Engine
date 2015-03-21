package environment;

import serialization.*;
import layout.*;
import boardObjectModels.DataHandler;
import boardObjectModels.ObservableBoardHolder;

public abstract class AbstractEnvironment {
	protected ObservableBoardHolder myBoardHolder;
	protected DataHandler myDataHandler;
	public AbstractLayout myGameView;

	public AbstractLayout getView(){
		return myGameView;
	}
}
