package serialization;

import java.lang.reflect.Type;

import boardObjectModels.Board;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;



public class boardSerialiser implements JsonSerializer<Board>{

	@Override
	public JsonElement serialize(Board src, Type typeOfSrc,
			JsonSerializationContext context) {

		return new JsonPrimitive(src.toString());
	}

}

