package attributes;


import inputs.GameInfo;
import gameObjectModel.GameObject;

public abstract class AbstractAttribute extends GameInfo{

	protected Double myValue; 
	private String myName;
	
	public AbstractAttribute(String name, Double attributeValue){
	    myName = name;    
		myValue = attributeValue; 
		
	}
	
	public abstract void action(GameObject obj); 

	
	public Double getValue(){
		return myValue; 
	} 
	
	public void setValue (double newValue){
	    myValue = newValue;
	}
	
	public String getName(){
        return myName; 
    }
	
	public String toString(){
	    return this.getClass().getSimpleName(); 
	}


	@Override
	public boolean equals(Object o){
		
		if ((o instanceof AbstractAttribute)) return (myValue.equals(((AbstractAttribute) o).getValue()))&&
    			( myName.equals(((AbstractAttribute) o).getName()))  ; 
	       return false;  
	}
	
}
