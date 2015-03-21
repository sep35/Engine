package inputs;

public class LevelUpdate extends AbstractUpdate {
    private String myBehavior;
    
    public LevelUpdate(String s){
        myBehavior = s;
    }
    
    public String getBehavior(){
        return myBehavior;
    }
    
    @Override
    public AbstractUpdate getMyOpposite () {
        return null;
    }

}
