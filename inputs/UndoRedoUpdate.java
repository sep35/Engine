package inputs;

public class UndoRedoUpdate extends AbstractUpdate{
	
	private boolean isUndo; 
	
	public UndoRedoUpdate(boolean undo){
		isUndo = undo; 
	}

	public boolean isUndo(){
		return isUndo; 
	}

	public boolean isRedo(){
		return !isUndo; 
	}

	@Override
	public AbstractUpdate getMyOpposite() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
