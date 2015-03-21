package serialization;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.Patch;

import com.google.gson.InstanceCreator;

public class PatchInstanceCreator implements InstanceCreator<Patch>{
	
	public Patch createInstance(Type type)
	   {
		List<Integer> dimensions= new ArrayList<Integer>();
	      return new Patch(dimensions, 0.0);
	   }
	
}
