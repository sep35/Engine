package inputs;

public class RestartUpdate extends AbstractUpdate{
	
	private boolean isRestart;
	
	public RestartUpdate(boolean restart){
		isRestart = restart;
	}
	
	public boolean isRestart(){
		return isRestart;
	}

	@Override
	public AbstractUpdate getMyOpposite() {
		// TODO Auto-generated method stub
		return null;
	}
}
