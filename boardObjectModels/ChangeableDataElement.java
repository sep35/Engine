package boardObjectModels;

public abstract class ChangeableDataElement {
   private boolean isChanged;
   public ChangeableDataElement(){
       isChanged = false;
   }
   protected void setChanged(boolean b){
       isChanged = b;
   }
   public boolean getChanged(){
       return isChanged;
   }
   
   public abstract String getName();
    
}
