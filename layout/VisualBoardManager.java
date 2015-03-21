package layout;

import gameObjectModel.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import boardObjectModels.Board;
import boardObjectModels.Patch;

public class VisualBoardManager {
    private ResourceBundle myConstants;
    public static final String CONSTANTS_PATH = "resources/constants";
    private List<Integer> myDims;
    public VisualBoardManager(AbstractLayout layout){
        myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
        
        myDims = layout.getBoard().getDims();
        }
    
    
    
    public List<Patch> getAvailablePatches(List<Integer> coords, Board board){
        GameObject obj = board.getGameObjectAt(coords);
        List<Patch> avail = new ArrayList<Patch>();
        List<Patch> obstructors = new ArrayList<Patch>();

        if(obj!=null){
        List<List<Integer>> deltas = obj.getMotionRange().getDeltas();
        for(List<Integer> list : deltas){
            int x = coords.get(0)+list.get(0);
            int y = coords.get(1)+list.get(1);
            if(!(x>=myDims.get(0)||x<0||y>=myDims.get(1)||y<0)){
                Patch p = board.getPatchAt(Arrays.asList(x,y));
                if(p.getGameObject()==null) avail.add(board.getPatchAt(Arrays.asList(x,y)));
                else obstructors.add(board.getPatchAt(Arrays.asList(x,y)));
                }  
        }
        
        if(!obj.getMotionRange().canJump()){
            for(Patch p:obstructors){
                int deltaX = p.getPosition().get(0) - coords.get(0);
                int deltaY = p.getPosition().get(1) - coords.get(1);
                List<Patch> toRemove = new ArrayList<Patch>();
                for(Patch a:avail){
                    int testX = a.getPosition().get(0) - p.getPosition().get(0);
                    int testY = a.getPosition().get(1) - p.getPosition().get(1);
                    if(((deltaX>=0==testX>=0)&&(deltaY>=0==testY>=0))){
                        if(deltaX!=0&&testX!=0){
                            if(deltaY/deltaX==testY/testX) toRemove.add(a);
                        }
                        else if(deltaY!=0&&testY!=0){
                            if(deltaX/deltaY==testX/testY) toRemove.add(a);
                        }
                    //    else{toRemove.add(a);}
                    }
                   // toRemove.add(a);
                    //}
                    }
                for(Patch r:toRemove) avail.remove(r);
                toRemove.clear();
            }
        }
        
        }
        return avail;
    }
    
    public List<Patch> getAttackablePatches(List<Integer> coords, Board board){
        GameObject obj = board.getGameObjectAt(coords);
        List<Patch> attack = new ArrayList<Patch>();

        if(obj!=null){
            List<List<Integer>> deltas = obj.getMotionRange().getDeltas();
            for(List<Integer> list : deltas){
                int x = coords.get(0)+list.get(0);
                int y = coords.get(1)+list.get(1);
                if(!(x>=myDims.get(0)||x<0||y>=myDims.get(1)||y<0)){
                    Patch p = board.getPatchAt(Arrays.asList(x,y));
                    if(p.getGameObject()!=null) attack.add(board.getPatchAt(Arrays.asList(x,y)));
                    }
            
            }
        
        }
        return attack;
    }

}
