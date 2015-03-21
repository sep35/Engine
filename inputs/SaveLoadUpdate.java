package inputs;

import java.nio.file.Path;

public class SaveLoadUpdate extends AbstractUpdate{
    private boolean isSave;
    private Path myFilePath;
    
   
    public SaveLoadUpdate(Path p, boolean bool){
        myFilePath = p;
        isSave = bool;
    }
    public boolean getIsSave(){
        return isSave;
    }
    public Path getPath(){
        return myFilePath;
    }
	@Override
	public AbstractUpdate getMyOpposite() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
 

}
