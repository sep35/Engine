package serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.Board;

import com.google.gson.InstanceCreator;

public class AbstractBoardInstanceCreator implements InstanceCreator<Board>{
	
	public Board createInstance(Type type){
		List<Integer> dimensions = new ArrayList<Integer>();
		dimensions.add(20);
		dimensions.add(20);
	      return new Board(dimensions);
	   }
	
}
