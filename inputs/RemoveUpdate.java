package inputs;

import java.util.List;

import gameObjectModel.GameObject;

public class RemoveUpdate extends ObjectUpdate{
      
    public RemoveUpdate(GameObject g, List<Integer> coord){
        myGameObject = g;
        myCoordinates = coord;
    }
    
     public AbstractUpdate makeReverse(){
            myOpposite = new ObjectUpdate( getGameObject(), getCoordinates()); 
            myOpposite.setReverse(true);
            return myOpposite; 
     }

     @Override 
     public AbstractUpdate getMyOpposite(){
         makeReverse(); 
         return myOpposite; 
     }
   
}
