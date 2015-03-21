package serialization;

import gameManager.TeamList;
import gameManager.Victory;
import gameManager.WinningConditionManager;
import gameObjectModel.AbstractRange;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

import popup.sPopup;
import attributes.AbstractAttribute;
import boardObjectModels.Board;
import boardObjectModels.ChangeableDataElement;
import boardObjectModels.ObservableBoardHolder;
import boardObjectModels.Patch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * DeSerialiser that reads a Json String and converts it into a board object
 * 
 * @author Tanaka Jimha
 *
 */

public class DeSerialiser
{
	private Board myBoard;
	private final String IO_ERROR_MESSAGE = "File reading error";
	private final String CLASS_NOT_FOUND = "Error, class not found";
	private ObservableBoardHolder myObsBHolder;

	public DeSerialiser(){

	}
	
	public ObservableBoardHolder deserialise(Path path){
			
		String json = read(path);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Serialiser mySerialiser = new Serialiser();
		/*gsonBuilder.registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>()).registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>());
		gsonBuilder.registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>()).registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>());
		gsonBuilder.registerTypeHierarchyAdapter(AbstractRange.class, new CustomSerialisation<AbstractRange>()).registerTypeHierarchyAdapter(AbstractRange.class, new CustomSerialisation<AbstractRange>());*/
		gsonBuilder.registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>()).registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>());
		mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, AbstractAttribute.class);
		mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, AbstractRange.class);
		//mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, ChangeableDataElement.class);
		mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, TeamList.class);
		mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, WinningConditionManager.class);
		mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, Victory.class);
		//mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, Board.class);
		mySerialiser.myRegisterTypeHeirchyAdapter(gsonBuilder, ObservableBoardHolder.class);

		
		gsonBuilder.registerTypeAdapter(Board.class, new AbstractBoardInstanceCreator());
		gsonBuilder.registerTypeAdapter(Patch.class, new PatchInstanceCreator());
		gsonBuilder.registerTypeAdapter(WinningConditionManager.class, new WinningManagerInstanceCreator());
		gsonBuilder.registerTypeAdapter(ObservableBoardHolder.class, new BoardHolderInstanceCreator());
		gsonBuilder.registerTypeAdapter(TeamList.class, new TeamListInstanceCreator());



		
		
		Gson gson = gsonBuilder.create();
		myObsBHolder = gson.fromJson(json, ObservableBoardHolder.class ) ;
		 

		return myObsBHolder;
	}

	
	private String read(Path path){
		try
		{
			FileInputStream fileIn = new FileInputStream(path.toString());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			String json = (String)in.readObject();
			in.close();
			fileIn.close();
			return json;
		}catch(IOException i)
		{
			//THIS WILL BE CHANGED WHEN ERROR DIALOGUES ARE CREATED
			sPopup popup = new sPopup(IO_ERROR_MESSAGE);
			return null;
		}catch(ClassNotFoundException c)
		{
			sPopup popup = new sPopup(CLASS_NOT_FOUND);
			return null;
		}
		
	}

}