package serialization;

import gameManager.TeamList;
import gameManager.WinningConditionManager;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;


public class WinningManagerInstanceCreator implements InstanceCreator<WinningConditionManager>{
	
	public WinningConditionManager createInstance(Type type){
		TeamList list = new TeamList();
		return new WinningConditionManager(list, 3);
	}
	
}
