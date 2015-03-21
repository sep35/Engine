package gameObjectModel;

import inputs.GameInfo;

import java.util.HashSet;
import java.util.List;

import attributes.AbstractAttribute;
import attributes.Descriptor;
import attributes.PassiveAttribute;


public class GameObject extends GameInfo{

	private List<AbstractAttribute> myAttributeList;
	private List<Descriptor> myDescriptors;
	private AbstractRange myMotionRange; 
	private AbstractRange myInteractRange;
	private PassiveAttribute myLifeManager; 



	/**
	 * constructor for the GameObject class
	 * @param myAttributes
	 */
	public GameObject(List<Descriptor> descrip, List<AbstractAttribute> myAttributes, AbstractRange motionRange, AbstractRange interactRange, PassiveAttribute lifeManager){
		myAttributeList = myAttributes;
		myMotionRange = motionRange;
		myInteractRange = interactRange;
		myDescriptors = descrip;
		myLifeManager = lifeManager; 
	}
	
	/**
	 * executes the action associated with the attribute at index attr on the GameObject that
	 * @param attr
	 * @param that
	 */
	protected void executeAttribute( AbstractAttribute attr, GameObject that ){
		attr.action(that);	
	}
	
	public AbstractRange getMotionRange(){
		return myMotionRange; 
	}
	
	public AbstractRange getAttackRange(){
        return myInteractRange; 
    }
	
	public List<AbstractAttribute> getAttributes(){
	    return myAttributeList; 
	}
	
	public List<Descriptor> getDescriptors(){
	    return myDescriptors;
	}
	
	public String toString(){    
	    if(!( myAttributeList == null))return myAttributeList.size()+""; 
	    else return "n" ; 
	}

	public PassiveAttribute getMyLifeManager() {
		return myLifeManager;
	}

	public void setMyLifeManager(PassiveAttribute lifeManager) {
		myLifeManager = lifeManager;
	}

	 @Override
	    public boolean equals(Object obj) {
	    // boolean equal = false;
	     
	       if ((obj instanceof GameObject)){
	           GameObject game = (GameObject) obj;
	           if(game.getDescriptors().size()!=this.getDescriptors().size()) return false;
	           int counter = 0;
	           for(Descriptor desc:game.getDescriptors()){
	               for(Descriptor myDesc: this.getDescriptors()){
	                   if(desc.getName().equals(myDesc.getName())&&desc.getValue().equals(myDesc.getValue())){
	                       counter++;
	                   }
	               }
	           }
	           return counter==this.getDescriptors().size();
	       }
	       return false;  
	    }
}
