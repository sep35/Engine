package serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.Board;

import com.google.gson.InstanceCreator;

public class TwoDPatchHolderInstanceCreator implements InstanceCreator<Board> {
	public Board createInstance(Type type)
	   {
		List<Integer> dimensions= new ArrayList<Integer>();
	      return new Board(dimensions);
	   }
}
