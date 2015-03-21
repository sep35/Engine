package gameObjectModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LateralRange extends AbstractRange{
    private Map<String, Integer> myDirections;
    
    public LateralRange(Map<String,Integer> directions, boolean jump, String name){
        super(name, jump);
        myDirections = directions;
    }
    @Override
    public List<List<Integer>> getDeltas(){
        List<List<Integer>> deltas = new ArrayList<List<Integer>>();
        int horiz = myDirections.get("horizontal");
        int vert =  myDirections.get("vertical");
        int diag =  myDirections.get("diagonal");        
/*
        for(int i = -horiz, j = -vert, k = -diag; i<= horiz + vert + diag; i++, j++ , k++){
            if(  i<=horiz)deltas.add(Arrays.asList(i, 0));
            if(j<= vert) deltas.add(Arrays.asList(0,i));
            if(k <= diag  ){
                deltas.add(Arrays.asList(k,k));
                deltas.add(Arrays.asList(k,-k));
            }
            if( !(i<=horiz) && !(j<=vert) && !(k <= diag))  break; 
        } 
  */        
        for(int i = -horiz; i<=horiz; i++){ 
            if(i!=0) deltas.add(Arrays.asList(i, 0));
            }
        for(int i = -vert; i<=vert; i++){
            if(i!=0) deltas.add(Arrays.asList(0,i));
        }
        for(int i = -diag; i<=diag; i++){
            if(i!=0){
            deltas.add(Arrays.asList(i,i));
            deltas.add(Arrays.asList(i,-i));
            }
        }        
        return deltas;        
    }
    

}
