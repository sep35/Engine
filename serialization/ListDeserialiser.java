package serialization;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ListDeserialiser<T> {

	private List<T> deserialiseList(String json, Object type){

		Gson gson = new Gson();
		Type objectType = new TypeToken<Collection<T>>() {}.getType();
		List<T> list = gson.fromJson(json, objectType);
		
		return list;


	}
	public static void main(String[] args) {
		
	}
}
