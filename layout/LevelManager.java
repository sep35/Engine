package layout;

import gameManager.Victory;

public class LevelManager {
    private AbstractLayout myLayout;
    public LevelManager(AbstractLayout layout){
        myLayout = layout;
    }
    
    public void update(Victory v){
        if(v!=null&&v.hasWon()){
            myLayout.setWinningTeam(v.whichTeam().getID());
        }
    }
}
