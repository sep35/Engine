package boardObjectModels;

import inputs.GameInfo;
import inputs.InfoUpdate;

import java.util.List;

import util.Reflection;

public class InfoBank extends ChangeableDataElement{
    private List<GameInfo> myList;
    private String myType;
    public InfoBank(List<GameInfo> obj){
        myList = obj;
    }
    @Override
    public String getName(){
        return myType;
    }
    
    public String getContent(){
        StringBuilder sb = new StringBuilder();
        for(GameInfo o: myList){
            sb.append(o.toString()+";");
        }
        return sb.toString();
    }
    
    public void updateState(InfoUpdate o){
        Reflection.callMethod(this, o.getBehavior(), o.getInfo());
    }
    public List<GameInfo> getList(){
        return myList;
    }
    
    protected void add(GameInfo o){
        for(GameInfo game:myList){
        if(game.equals(o)) return;
        }
        myList.add(o);
        myType = o.getType();

    }
    protected void remove(GameInfo o){
        if(myList.size()>0){
        if(myList.contains(o)) myList.remove(o); 
        setChanged(true);
        }
    }
    protected void edit(GameInfo o){
        if(myList.contains(o)) myList.remove(o); 
        myList.add(o); 
        // TODO I don't think this edit method works       
    }
}
