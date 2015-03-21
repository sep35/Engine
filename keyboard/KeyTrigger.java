package keyboard;

/**
 * 
 * @author keng
 *
 */

public class KeyTrigger {
    
    private int triggerCount = 0;
    private boolean myIsTriggered = false;
    
    public int getTriggerCount() {
        return triggerCount;
    }
    
    public boolean isTriggered() {
        return myIsTriggered;
    }
    
    public void trigger(boolean isTriggered) {
       myIsTriggered = isTriggered; 
       if(isTriggered) triggerCount++;
    }
}
