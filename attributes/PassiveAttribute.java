package attributes;


import gameObjectModel.GameObject;

public class PassiveAttribute extends AbstractAttribute {

	
    public PassiveAttribute (String name, Double attributeValue) {
        super(name, attributeValue);
    }

    @Override
    public void action (GameObject obj) {        
    }
}
