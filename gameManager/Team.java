package gameManager;

import gameObjectModel.GameObject;
import inputs.GameInfo;

import java.util.ArrayList;
import java.util.List;

public class Team extends GameInfo {

	
	private String ID; 
	private List<GameObject> TeamMembers; 
	private int ListInitSize; 
	private WinningCondition myWinningCondition; 
	

	public Team(String id, WinningCondition myWCd){
		ID = id; 
		TeamMembers = new ArrayList<>(); 
		myWinningCondition = myWCd; 
		
	}
	
	/*public Team(){
		this.ID = "";
		TeamMembers = new ArrayList<>(); 
		myWinningCondition = new WinningCondition();
		
	}*/
	
	
	
	
	public WinningCondition getMyWinningCondition() {
		return myWinningCondition;
	}

	public void setMyWinningCondition(WinningCondition myWCondition) {
		myWinningCondition = myWCondition;
	}
	
	public String getID(){
		return ID; 
	} 
	
	public void addGameObject( GameObject obj){
		TeamMembers.add(obj); 
		ListInitSize = TeamMembers.size();
	}

	public void addGameObjectList(List<GameObject> toAdd){
		TeamMembers.addAll(toAdd); 
		ListInitSize = TeamMembers.size();
	}

	protected void remove(){
		if(TeamMembers.size() != 0) TeamMembers.remove(TeamMembers.size() -1); 
	}
	
	public int getCurrTeamSize(){
		return TeamMembers.size(); 
	}
	
	public int getInitSize(){
		return ListInitSize; 
	}
	
	public double getAlivePct(){
		//return (double) TeamMembers.size()/ (double)ListInitSize; 
		return 10.0;
	}

	public String toString(){
		return ID; 
	}

	public void setID(String string) {
		ID = string;
	}




    @Override
    public boolean equals (Object o) {
        if(o instanceof Team){
            Team team = (Team) o;
            return ID.equals(team.getID());
        }
        return false;
    }

}
