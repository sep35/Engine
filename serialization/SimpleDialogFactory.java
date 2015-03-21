package serialization;

import util.SimpleDialog;

public class SimpleDialogFactory {



	
	public  void emptyUndo(){
		SimpleDialog emptyUndo = new SimpleDialog( "Warning", "There are no more commands to undo", 
		        300, 300); 
		emptyUndo.show();
	}
	
	public void emptyRedo(){
		SimpleDialog emptyRedo = new SimpleDialog("Warning", "There are no more commands to redo", 
		        300, 300); 
		emptyRedo.show(); 
	}
	
	
	
}
