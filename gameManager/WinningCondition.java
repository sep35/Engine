package gameManager;

import java.util.HashMap;
import java.util.Map;

public class WinningCondition {

	
	private int surviveTurns; 
	
	private Map<Team, Double> killWinPct; 
	
	private String SurviveDestroyOperator; 

	private String DestroyOperator; 
	
	
	
	public WinningCondition(){
		killWinPct = new HashMap<>(); 
		
	}
	

	public WinningCondition(int survTurns, String SurvDestOp, String DestOp) {
		// TODO Auto-generated constructor stub
		surviveTurns = survTurns; 
		SurviveDestroyOperator = SurvDestOp; 
		DestroyOperator = DestOp; 
		killWinPct = new HashMap<>();
	}


	public void updateWinningCondition(Team teamName, int WinPct){
			killWinPct.put(teamName, (double) WinPct/100); 
	} 
	
	public double getWinPct( Team teamName){
		return killWinPct.get(teamName); 	
	}
	
	public int getSurviveTurns() {
		return surviveTurns;
	}

	public void setSurviveTurns(int surviveTurns) {
		this.surviveTurns = surviveTurns;
	}

	public String getSurviveDestroyOperator() {
		return SurviveDestroyOperator;
	}

	public void setSurviveDestroyOperator(String surviveDestroyOperator) {
		SurviveDestroyOperator = surviveDestroyOperator;
	}

	
	public String getDestroyOperator() {
		return DestroyOperator;
	}

	public void setDestroyOperator(String destroyOperator) {
		DestroyOperator = destroyOperator;
	}
	
}
