package attributes;

import inputs.GameInfo;


public class Descriptor extends GameInfo{

    protected String myValue; 
    private String myName;
    
    public Descriptor(String name, String attributeValue){
        myName = name;    
        myValue = attributeValue; 
        
    }
    
    public String getValue(){
        return myValue; 
    }

    public void setValue (String newValue){
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
    	if ((o instanceof Descriptor)) return (myValue.equals(((Descriptor) o).getValue()))&&
    			( myName.equals(((Descriptor) o).getName())) ; 
	    return false;  
    	
    	
    }
}

