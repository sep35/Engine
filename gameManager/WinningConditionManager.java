package gameManager;

import gameObjectModel.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class WinningConditionManager extends Observable implements Observer {

	private TeamList AllTeams; 
	private int numMoves;  
	private int turnCount; 
	private Team activeTeam; 
	private Victory currVictor;
	
	
	
	public WinningConditionManager( TeamList myTeamList, int numMov){
		// TODO get numMoves from front end
		AllTeams = myTeamList;  
		turnCount = 1; 
		numMoves = numMov; 
	}

	public void setNumMoves( int numMov){
		numMoves = numMov; 
	}

	public Victory manage(){
	
		if(activeTeam == null) activeTeam = AllTeams.getHeadTeam(); 
		activeTeam = AllTeams.getActiveTeam(turnCount%numMoves ==0 ); 
		
		while(activeTeam.getAlivePct()==0) activeTeam = AllTeams.getActiveTeam(true);   
		turnCount++; 
		currVictor = new Victory( hasWin(), activeTeam);
		System.out.println(currVictor.toString());
		return  currVictor; 
	}
	

	public void addTeam( Team aTeam){
		AllTeams.addTeam(aTeam);
	}
	
	

	private boolean hasWin(){
		WinningCondition currWinCondition = activeTeam.getMyWinningCondition();
		List<Boolean> temp = new ArrayList<>();
		temp.add(hasDestroyWin()); 
		temp.add(hasSurvivalWin()); 
		
		if(currWinCondition.getSurviveDestroyOperator() == "or" ) return or(temp); 
		return and(temp); 
	}
	
	

	private boolean hasDestroyWin(){
		
		List<Boolean> tempDestroy = new ArrayList<>();
		WinningCondition currWinCondition = activeTeam.getMyWinningCondition(); 
		for(Team t :AllTeams ){ 
			if(!t.equals(activeTeam)){  
				tempDestroy.add( (1 - currWinCondition.getWinPct(t)) >= t.getAlivePct());  
				}
		}
		
	
		//System.out.println(tempDestroy.size()); 
		if(currWinCondition.getDestroyOperator() == "or" ) return or(tempDestroy); 
		return and(tempDestroy); 
		
	}
	
	
	private boolean hasSurvivalWin(){
		WinningCondition currWinCondition = activeTeam.getMyWinningCondition();
		if(currWinCondition.getSurviveTurns() == -1) return false; 
		return (currWinCondition.getSurviveTurns() <= turnCount ); 
	}

	protected boolean or(List<Boolean> toOr){
		boolean temp = toOr.get(0); 
		for( int i = 1; i<toOr.size(); i++ ){
			temp = temp||toOr.get(i); 
		}
		return temp; 
	}

	protected boolean and(List<Boolean> toOr){
		boolean temp = toOr.get(0); 
		for( int i = 1; i<toOr.size(); i++ ){
			temp = temp&&toOr.get(i); 
		}
		return temp; 
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		manage(); 
		
	}
	
	
	public static void main(String[] args){
		
		WinningCondition testWinCond = new WinningCondition(); 
		testWinCond.setDestroyOperator("and"); 
		testWinCond.setSurviveDestroyOperator("or");
		testWinCond.setSurviveTurns(100000);
		Team team1 = new Team("boorbies", testWinCond); 
		Team team2 = new Team("no-boorbies", testWinCond);
		testWinCond.updateWinningCondition(team2, 50);
		testWinCond.updateWinningCondition(team1, 50
				);
		team1.addGameObject( new GameObject(null, null, null, null, null));
		team1.addGameObject( new GameObject(null, null, null, null, null));
		team1.addGameObject(new GameObject(null,null, null, null, null ));
		team2.addGameObject( new GameObject(null, null, null, null, null));
		team2.addGameObject( new GameObject(null, null, null, null, null));
		TeamList myTeamList = new TeamList(); 
		myTeamList.addTeam(team1);
		myTeamList.addTeam(team2);
		WinningConditionManager wcm = new WinningConditionManager(myTeamList,1); 
	
		
		for( int i =0; i < 10; i ++){
		System.out.println( wcm.manage()); 	
		System.out.println( team1.getCurrTeamSize()); 
		team1.remove();
		}
		//System.out.println("Blurb");

		 
	
	}
		
}
