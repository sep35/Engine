package boardObjectModels;

import java.util.List;

import gameObjectModel.GameObject;

public class Patch{

	private List<Integer> myPosition;
	private GameObject myGameObject; 
	private boolean isActive; 
	
	public Patch(){}; 
	
	public Patch( List<Integer> position, double resources){ 
		myPosition = position; 
	}
	
	public Patch(GameObject obj, List<Integer> position, double resources){
		myPosition = position; 
		myGameObject = obj; 
	}
	
	
	public GameObject getGameObject(){
		return myGameObject; 
	}

	
	protected void setGameObject(GameObject objToSet){
		myGameObject = objToSet; 
	}

	
	
	public List<Integer> getPosition() {
		return myPosition; 
	}
	
	public void deleteGameObject(){
		myGameObject = null; 
	}
	
	public void setPosition(List<Integer> toSet){
		myPosition = toSet;
	}
	
	
	public boolean isActive(){
		return isActive; 
	}
	
	public void toggleActive(){
		isActive = !isActive; 
	}
	
	public String toString(){
//       if( myGameObject.equals(null)) return " my Position is null"; 
//       else
	    if( !(myGameObject == null)) return  myGameObject.toString();
	    else return "."; 
	}

	
	protected boolean isEmpty(){
		return myGameObject ==null; 
	}
	
	public void setResources(double resourceAmount) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNeighbors(GameObject neighborSetter) {
		// TODO Auto-generated method stub
		
	}

}


