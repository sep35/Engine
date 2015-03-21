package boardObjectModels;

import gameManager.Team;
import gameManager.TeamList;
import gameManager.Victory;
import gameManager.WinningConditionManager;
import inputs.GameInfo;
import inputs.InfoUpdate;
import inputs.LevelUpdate;
import inputs.MoveUpdate;
import inputs.ObjectUpdate;
import inputs.RemoveUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class ObservableBoardHolder extends Observable {
	private Board myBoard; 
	private List<Board> myLevels;
	private Map<String, InfoBank> myGameInfoMap;
	private List<ChangeableDataElement> myElements;
	private List<String> myBankTypes;
	private Victory myVictory;
	private LevelReflector myLevelReflector;

	private WinningConditionManager myWinManager; 
	private TeamList myTeamList; 
	
	public ObservableBoardHolder(Board board, int numTerms){
	    myBankTypes = Arrays.asList("ActiveAttribute", "PassiveAttribute", "GameObject", "ImageDescriptor", "Team");
	    myBoard = board;
	    myBoard.setChanged(true);
	    myTeamList = new TeamList();  
	    myWinManager = new WinningConditionManager(myTeamList, numTerms);
	    initializeBanks();
	    myElements = new ArrayList<ChangeableDataElement>();
	    myElements.add(myBoard);	
	    myLevels = new ArrayList<Board>();
	    myLevels.add(myBoard);
	    myLevelReflector = new LevelReflector();
	    
	    for(InfoBank b : myGameInfoMap.values()) myElements.add(b);
	    setChangedAndNotify();
	}
  protected void addObject(ObjectUpdate input){
     myBoard.setGameObject(input.getGameObject(),input.getCoordinates()); 
     setChangedAndNotify();
	}	

	protected void changeObjectOnBoard(Object arg){
		if( arg instanceof RemoveUpdate){
			removeObject((RemoveUpdate) arg);
		}
		else if( arg instanceof MoveUpdate){
		 
		    MoveUpdate temp = ( MoveUpdate) arg; 
		    removeObject( temp.getMyRemove()); 
		    addObject(temp.getMyAdd()); 
		}
		
		else if( arg instanceof ObjectUpdate){
			addObject((ObjectUpdate)arg);
		} 
		
	}
 	
	protected void updateBoard(Board board){
	    myVictory = myWinManager.manage();
		myBoard = board;
		setChangedAndNotify();
	}
	
	public Board getBoard(){
		return myBoard;
	}
	
	protected void setBoard(Board board){
		myBoard = board;
	}
	
	
	private void setChangedAndNotify(){
	    List<String> changedElements = new ArrayList<String>();
	    for(ChangeableDataElement e : myElements){
	        if(e.getChanged()) changedElements.add(e.getName());
	        e.setChanged(false);
	    }
	    setChanged();
		notifyObservers(new Output(myVictory, changedElements));
	}
	private void removeObject(RemoveUpdate in){
	    myBoard.deleteGameObjectAt(in.getCoordinates());
	    setChangedAndNotify();
	}
	
	private void initializeBanks(){
	    myGameInfoMap = new HashMap<String, InfoBank>();
	    for(String s: myBankTypes){
	        myGameInfoMap.put(s, new InfoBank(new ArrayList<GameInfo>()));
	    }
	    
	}
	protected void updateInfo(InfoUpdate in){
	   InfoBank b = myGameInfoMap.get(in.getInfo().getType());

	   if(in.getInfo() instanceof Team ){
		   myTeamList.addTeam( (Team)in.getInfo()); 
	   }
	   b.updateState(in);
	   
	   b.setChanged(true);
	   setChangedAndNotify();
	}
	public InfoBank getMapping(String s){
	    return myGameInfoMap.get(s);
	}
	public void renderBoard(){
	    setChangedAndNotify();
	}
	
	protected void updateLevel(LevelUpdate in){
	    int index = myLevels.indexOf(myBoard);
	  int move = index + myLevelReflector.update(in.getBehavior(), myLevels, myBoard);
	  if(move<myLevels.size()&&move>=0) myBoard = myLevels.get(move);
	  setChangedAndNotify();
	}
}
