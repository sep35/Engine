package serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.Board;
import boardObjectModels.ObservableBoardHolder;

import com.google.gson.InstanceCreator;

public class BoardHolderInstanceCreator implements InstanceCreator<ObservableBoardHolder>{

	public ObservableBoardHolder createInstance(Type type){
		List<Integer> dimensions = new ArrayList<Integer>();
		dimensions.add(20);
		dimensions.add(20);

		Board myBoard = new Board(dimensions);
		
		return new ObservableBoardHolder(myBoard, 3);
	}
}
