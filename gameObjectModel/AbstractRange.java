package gameObjectModel;

import java.util.List;

public abstract class AbstractRange {
    private boolean canJump;
    private String myType;
    public abstract List<List<Integer>> getDeltas();
    public AbstractRange(String s, boolean b){
        canJump = b;
        myType = s;
    }
    public AbstractRange(){
        this("", true);
    } 
    public boolean canJump(){
        return canJump;
    }
    public String getType(){
        return myType;
    }
}
