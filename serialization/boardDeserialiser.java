package serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.Board;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class boardDeserialiser implements JsonDeserializer<Board> {


	@Override
	public Board deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.parseInt(json.getAsJsonPrimitive().getAsString()));

		return new Board(list);
	}
}