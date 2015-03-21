package boardObjectModels;

import gameManager.Victory;

import java.util.List;

public class Output {
    private List<String> myElements;
    private Victory myVictory;
    protected Output(Victory v, List<String> elems){
        myVictory = v;
        myElements = elems;
    }
    
    public List<String> getElements(){
        return myElements;
    }
    
    public Victory getVictory(){
        return myVictory;
    }

}
