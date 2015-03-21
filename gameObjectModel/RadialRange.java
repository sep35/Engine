package gameObjectModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RadialRange extends AbstractRange{

    private int rangeVal; 
    
    public RadialRange(boolean jump, int distance, String name ){ 
        super(name, jump);
        rangeVal = distance; 
    }
    
    public int getRange(){
        return rangeVal; 
    }

    @Override
    public List<List<Integer>> getDeltas () {
        List<List<Integer>> deltas = new ArrayList<List<Integer>>();
        for(int i = -rangeVal; i<=rangeVal; i++){
            for(int j = -rangeVal; j<=rangeVal; j++){
                if(!((i==0)&&(j==0))) deltas.add(Arrays.asList(i,j));
            }   
        }
        return deltas;
    }

}
