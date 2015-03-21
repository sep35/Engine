package inputs;

public abstract class AbstractUpdate{
    
    protected AbstractUpdate myOpposite;
    protected boolean reverse;
    
    public AbstractUpdate(){
        reverse = false;
    }
    
    public abstract AbstractUpdate getMyOpposite(); 
    
    
    public boolean isReverse(){
        return reverse;
    }
    
    public void setReverse(boolean b){
        reverse = b;
    }
	public void executeMethod() {
		// TODO Auto-generated method stub
		
	}

	public String toString(){
	    return this.getClass().getSimpleName(); 
	}
	
}
