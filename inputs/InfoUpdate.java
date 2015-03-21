package inputs;

import attributes.AbstractAttribute;

public class InfoUpdate extends AbstractUpdate {
    private GameInfo myInfo;
    private String myBehavior;
    public InfoUpdate(GameInfo att, String beh){
        myInfo = att;
        myBehavior = beh;
    }

    @Override
    public AbstractUpdate getMyOpposite() {
        return null;
    }
    public GameInfo getInfo(){
        return myInfo;
    }
    public String getBehavior(){
        return myBehavior;
    }
    
}
