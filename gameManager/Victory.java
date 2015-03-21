package gameManager;

public class Victory {
	
	private boolean hasWon; 
	private Team winningTeam; 
	
	protected Victory( boolean won, Team winTeam){
		hasWon = won; 
		winningTeam = winTeam;
	}
	

	public boolean hasWon(){
		return hasWon; 
	}
	
	
	public Team whichTeam(){
		return winningTeam; 
	}

	public String toString(){
		return hasWon +" "+ winningTeam.getID(); 
	}

}


