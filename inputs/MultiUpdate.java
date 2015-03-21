package inputs;

import java.util.ArrayList;
import java.util.List;

public class MultiUpdate extends AbstractUpdate{
    
    private List<AbstractUpdate> myInputs;
    
    public MultiUpdate(List<AbstractUpdate> inputs){
        myInputs=inputs;
    }
    
    public List<AbstractUpdate> getInputs(){
        return myInputs;
    }

    @Override
    public AbstractUpdate getMyOpposite () {
        makeReverse(); 
        return myOpposite; 
    }

     public AbstractUpdate makeReverse(){
            List<AbstractUpdate> opps = new ArrayList<AbstractUpdate>();
            for(AbstractUpdate i : myInputs) opps.add(i.getMyOpposite());
            myOpposite = new MultiUpdate(opps); 
            myOpposite.setReverse(true);
            return myOpposite; 
     }
}
