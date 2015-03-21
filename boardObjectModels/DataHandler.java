package boardObjectModels;


import gameObjectModel.GameObject;
import inputs.AbstractUpdate;
import inputs.GameInfo;
import inputs.InfoUpdate;
import inputs.InteractUpdate;
import inputs.LevelUpdate;
import inputs.MultiUpdate;
import inputs.ObjectUpdate;
import inputs.SaveLoadUpdate;
import inputs.UndoRedoUpdate;

import java.beans.Introspector;
import java.nio.file.Path;
import java.util.Observable;
import java.util.Observer;

import serialization.DeSerialiser;
import serialization.Serialiser;
import util.Reflection;
import attributes.AbstractAttribute;


public class DataHandler implements Observer{

	private ObservableBoardHolder myBoardHolder; 
	private Serialiser mySerialiser;
	private DeSerialiser myDeSerialiser;
	private HistoryStack myStates;

	public DataHandler(ObservableBoardHolder board) {
	    mySerialiser = new Serialiser();
	    myDeSerialiser = new DeSerialiser();
		myStates = new HistoryStack();
		myBoardHolder = board;

	}

	private void writeData(ObservableBoardHolder myBoardHolder, Path p) {
	    
		mySerialiser.serialise(myBoardHolder, p);

	}
	private void readData(Path p) {
	    myBoardHolder = myDeSerialiser.deserialise(p);

	    myBoardHolder.updateBoard(myBoardHolder.getBoard());
	}


	public void undo() {
		AbstractUpdate input = myStates.undo();
		try{
		update(null, input);
		}
		catch( NullPointerException e){}
	}
	public void redo() {
		AbstractUpdate input = myStates.redo();
		try{
		update(new Observable(), input); }
		catch(NullPointerException e){}
	}
	
	@Override
	public void update (Observable o, Object arg) {
		String methodName = Introspector.decapitalize(arg.getClass().getSimpleName()); 
		Reflection.callMethod(this, methodName, arg);
	}

	
	private void objectUpdate(Object arg){
	    ObjectUpdate obj = (ObjectUpdate) arg;
	    infoUpdate(new InfoUpdate((GameInfo) obj.getGameObject(), "add"));
	    
	   	revChecker((AbstractUpdate) arg);
	   	
	}
	
	private void moveUpdate(Object arg){
        revChecker((AbstractUpdate) arg);       
    }
	
	
	private void interactUpdate(Object arg){
       InteractUpdate input = (InteractUpdate) arg;
       GameObject receiver = input.getActor().getGameObject();
       AbstractAttribute attr = input.getAttribute();
       attr.action(receiver);
       myBoardHolder.updateBoard(myBoardHolder.getBoard());     
    }

	private void revChecker(AbstractUpdate input) {
		myBoardHolder.changeObjectOnBoard(input);
		if(!input.isReverse()){
	   	addState(input);
	   	}
	}
	
	private void attributeUpdate(AbstractUpdate input){
	    InfoUpdate in = (InfoUpdate) input;
	   myBoardHolder.updateInfo(in);
	}
	
	
	private void removeUpdate(Object arg){
	    revChecker((AbstractUpdate) arg); 
	}

	public void undoRedoUpdate(Object arg){
	    
		UndoRedoUpdate input = (UndoRedoUpdate) arg;
		Boolean undo = input.isUndo();

		if(undo){
			undo();
		}
		else{
			redo();
		}
	}
	
	
	private void saveLoadUpdate(Object arg){
        SaveLoadUpdate input = (SaveLoadUpdate) arg;
        Boolean save = input.getIsSave();
        if(save){
            writeData(myBoardHolder, input.getPath());
        }
        else{
            readData(input.getPath());
        }
    }
	
	private void multiUpdate(Object arg){
	    MultiUpdate input = (MultiUpdate) arg;
	    for(AbstractUpdate i : input.getInputs()){
	        update(null, i);
	    }
	}
	
	private void infoUpdate(Object arg){
        InfoUpdate input = (InfoUpdate) arg;
        myBoardHolder.updateInfo(input);
    }
	
	private void levelUpdate(Object arg){
	    LevelUpdate input = (LevelUpdate) arg;
	    myBoardHolder.updateLevel(input);
	}
	
	private void addState(AbstractUpdate input) {
		myStates.add(input);
	}

	public AbstractUpdate returnCurrentState() {
		return myStates.getCurrInput();
	}


}
