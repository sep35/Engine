package attributes;

import gameObjectModel.GameObject;

public class ActiveAttribute extends AbstractAttribute {
    private String myActionReceiver;
    private boolean enemyActor;
    private boolean friendActor;

    public ActiveAttribute (String name, Double attributeValue, 
            String receiver, boolean enemy, boolean friendly) {
        super(name, attributeValue);
        myActionReceiver = receiver;
        enemyActor = enemy;
        friendActor = friendly;
    }
    
    @Override
    public void action (GameObject obj) {
        for(AbstractAttribute a : obj.getAttributes()){
            if(a.getName().equals(myActionReceiver)){
                a.setValue(a.getValue()+myValue);
            }
        }
    }
    
    public boolean isEnemyActor(){
        return enemyActor;
    }
    public boolean isFriendActor(){
        return friendActor;
    }
    public String getReceiver(){
        return myActionReceiver;
    }

    @Override 
    public boolean equals (Object o){
    	return super.equals(o) 
    			&& (((ActiveAttribute) o).getReceiver() ).equals(myActionReceiver)
    			&& (((ActiveAttribute) o).isFriendActor() ) ==(friendActor)
    			&& (((ActiveAttribute) o).isEnemyActor() ) == (enemyActor); 
    }    
}
