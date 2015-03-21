package inputs;


import gameObjectModel.GameObject;

import java.util.List;

public class MoveUpdate extends ObjectUpdate{

	private List<Integer> myFinalPos; 
	private RemoveUpdate myRemove;
	private ObjectUpdate myAdd; 
	
	
	public MoveUpdate(GameObject g, List<Integer> coord,List<Integer> finalCoord){
		super(g,coord); 
	myRemove = new RemoveUpdate( g, coord); 
	myAdd = new ObjectUpdate(g, finalCoord ); 
	}
	
	@Override
	public AbstractUpdate getMyOpposite() {
		// TODO Auto-generated method stub
		MoveUpdate myOpposite = new MoveUpdate(myGameObject, myFinalPos, myCoordinates ); 
		myOpposite.setReverse(true);
		return myOpposite;
	}

	
	public RemoveUpdate getMyRemove(){
	    return myRemove; 
	} 
	
	public ObjectUpdate getMyAdd(){
	    return myAdd; 
	} 

	
}
