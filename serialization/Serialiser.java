package serialization;

import gameManager.ITeamList;
import gameManager.TeamList;
import gameManager.Victory;
import gameManager.WinningConditionManager;
import gameObjectModel.AbstractRange;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
 * Serialiser that writes a gameboard into a JSON String using GSON
 * 
 * @author Tanaka Jimha
 *
 */

public class Serialiser {
	
	private final String ERROR_MESSAGE = "File writting error";
	
	public Serialiser(){

	}
	private Board myBoard;
	private ObservableBoardHolder myObsBHolder;


	/*public String serialise(Board board, Path path){
		
		File file = new File(path.toString());
		myBoard = board;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>()).registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>());
		gsonBuilder.registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>()).registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>());
		gsonBuilder.registerTypeHierarchyAdapter(AbstractRange.class, new CustomSerialisation<AbstractRange>()).registerTypeHierarchyAdapter(AbstractRange.class, new CustomSerialisation<AbstractRange>());

		
		gsonBuilder.registerTypeAdapter(Board.class, new AbstractBoardInstanceCreator());
		gsonBuilder.registerTypeAdapter(Patch.class, new PatchInstanceCreator());
		gsonBuilder.registerTypeAdapter(WinningConditionManager.class, new WinningManagerInstanceCreator());
		gsonBuilder.registerTypeAdapter(ObservableBoardHolder.class, new BoardHolderInstanceCreator());
		gsonBuilder.registerTypeAdapter(TeamList.class, new TeamListInstanceCreator());


		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(board); 
		write(file, json);
		return json;

	}
	*/
public String serialise(ObservableBoardHolder myOBH, Path path){
		
		File file = new File(path.toString());
		myObsBHolder = myOBH;
		GsonBuilder gsonBuilder = new GsonBuilder();
		/*gsonBuilder.registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>()).registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>());
		gsonBuilder.registerTypeHierarchyAdapter(AbstractRange.class, new CustomSerialisation<AbstractRange>()).registerTypeHierarchyAdapter(AbstractRange.class, new CustomSerialisation<AbstractRange>());
		*/gsonBuilder.registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>()).registerTypeHierarchyAdapter(ChangeableDataElement.class, new CustomSerialisation<ChangeableDataElement>());
		gsonBuilder.registerTypeHierarchyAdapter(ITeamList.class, new CustomSerialisation<ITeamList>()).registerTypeHierarchyAdapter(ITeamList.class, new CustomSerialisation<ITeamList>());

		myRegisterTypeHeirchyAdapter(gsonBuilder, AbstractAttribute.class);
		myRegisterTypeHeirchyAdapter(gsonBuilder, AbstractRange.class);
		//myRegisterTypeHeirchyAdapter(gsonBuilder, ChangeableDataElement.class);
		//myRegisterTypeHeirchyAdapter(gsonBuilder, TeamList.class);
		myRegisterTypeHeirchyAdapter(gsonBuilder, WinningConditionManager.class);
		myRegisterTypeHeirchyAdapter(gsonBuilder, Victory.class);
		//myRegisterTypeHeirchyAdapter(gsonBuilder, Board.class);
		myRegisterTypeHeirchyAdapter(gsonBuilder, ObservableBoardHolder.class);
		



		
		gsonBuilder.registerTypeAdapter(Board.class, new AbstractBoardInstanceCreator());
		gsonBuilder.registerTypeAdapter(Patch.class, new PatchInstanceCreator());
		gsonBuilder.registerTypeAdapter(WinningConditionManager.class, new WinningManagerInstanceCreator());
		gsonBuilder.registerTypeAdapter(ObservableBoardHolder.class, new BoardHolderInstanceCreator());
		gsonBuilder.registerTypeAdapter(TeamList.class, new TeamListInstanceCreator());




		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(myOBH); 
		write(file, json);
		return json;

	}

	public String prettySerialise(Board aBoard){
		myBoard = aBoard;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(aBoard); 
		return json;
	}
	
	private void write(File file, String json){
		try
		{
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(json);
			out.close();
			fileOut.close();

		}catch(IOException i)
		{
			sPopup popup = new sPopup(ERROR_MESSAGE);
			
		}
	}
	
	public <T> void myRegisterTypeHeirchyAdapter(GsonBuilder builder, T type){
		builder.registerTypeHierarchyAdapter(type.getClass(), new CustomSerialisation<T>()).registerTypeHierarchyAdapter(type.getClass(), new CustomSerialisation<T>());

	}
	
}
