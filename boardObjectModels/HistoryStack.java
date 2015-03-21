package boardObjectModels;

import java.util.Stack;

import serialization.SimpleDialogFactory;
import inputs.AbstractUpdate;
import inputs.*;

public class HistoryStack{

	private Stack<AbstractUpdate> executed; 
	private Stack<AbstractUpdate> undone; 
	private SimpleDialogFactory popupMaker;
	
	public HistoryStack (){
		executed = new Stack<>(); 
		undone = new Stack<>();
		popupMaker = new SimpleDialogFactory(); 
	}
	
	public void add( AbstractUpdate toPush){
		executed.push(toPush);
		if(!undone.isEmpty()) undone.clear();
	}
	
	public AbstractUpdate getCurrInput(){
		return executed.peek(); 
	}
	
	public AbstractUpdate undo(){
	    
	    AbstractUpdate input;
	    if(!executed.isEmpty())
	        {input = executed.pop();
	    undone.push(input);
	     return input.getMyOpposite();
	     } 
	   
	    popupMaker.emptyUndo();
	    return null; 
	}
	
	public AbstractUpdate redo(){
	    if(!undone.isEmpty()){
	    AbstractUpdate input = undone.pop();
	    executed.push(input);
	    input.setReverse(true);
        return input; 
	    }
	    popupMaker.emptyRedo();
	    return null;
	}
	
	public AbstractUpdate getLastUndone(){
	    return undone.peek(); 
	}



//	public static void main(String[] args){
//		HistoryStack temp = new HistoryStack(); 
//		AbstractUpdate in1 = new InfoUpdate(null, null); 
//		AbstractUpdate in2 = new InteractUpdate(null, null, null); 
//		AbstractUpdate in3 = new Save
//		
//		
//	}
}