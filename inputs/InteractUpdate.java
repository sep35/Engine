package inputs;

import attributes.AbstractAttribute;
import boardObjectModels.Patch;

public class InteractUpdate extends AbstractUpdate{
        
        private Patch myActor; 
        private Patch myReceiver;
        private AbstractAttribute myAttribute;
        

        public InteractUpdate(Patch actor, AbstractAttribute attribute, Patch receiver){
            myActor = actor;
            myReceiver = receiver;
            myAttribute = attribute;
        }
        
        public Patch getActor(){
           return myActor; 
        }
        public Patch getReceiver(){
            return myReceiver;
        }
        public AbstractAttribute getAttribute(){
            return myAttribute;
        }

		public AbstractUpdate oppositeMethod() {
			// TODO Auto-generated method stub
			return null;
		}

        @Override
        public AbstractUpdate getMyOpposite () {
            // TODO Auto-generated method stub
            return null;
        }
    
}
