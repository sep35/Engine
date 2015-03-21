package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import environment.*;

public class SceneManager {
	private Scene myScene;
	private int DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT, counter;
	private SharedToolbar topbar;
	private Random rand;
	protected TabPane environments;
	private Stage st;
	private List<Environment> createdEnvironment;
	private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";

	protected void createScene(Stage s) {
		rand = new Random();
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		DEFAULT_SCREEN_WIDTH = Integer.parseInt(myConstants.getString("Default_Screen_Width"));
		DEFAULT_SCREEN_HEIGHT = Integer.parseInt(myConstants.getString("Default_Screen_Height"));
		FlowPane root = new FlowPane();
		root.setStyle("-fx-background-color: Deepskyblue");
		myScene = new Scene(root, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		st = s;
		s.setScene(myScene);	
		s.setTitle("Environment");
		s.show();
		createToolBar(root);
	}

	private void createToolBar(FlowPane root) {
		topbar = new SharedToolbar();
		topbar.edit(root, this);
		topbar.createTab(root);
		createdEnvironment = new ArrayList<Environment>();
		environments = topbar.getTab();
		counter = 0;
	}

	protected void createEnvironment(String args) {
		st.setTitle(args+" Environment"); 
		DialogBox dimensions = new DialogBox();
		Environment environment = new Environment(args,
				dimensions.getXSize(),
				dimensions.getYSize(),
				dimensions.getNumMoves());
		Tab start = new Tab();	
		start.setText("Untitled " + counter);
		start.setContent(environment.getPane());
		createdEnvironment.add(environment);
		environments.getTabs().add(start);
		start.setStyle("-fx-background-color: " + randomColor());
		counter++;
	}

	protected void switchScene() {
		environments.getTabs().clear();
		for(int i = 0; i < createdEnvironment.size(); i++){
			Tab start = new Tab();
			createdEnvironment.get(i).switchEnvironment();
			start.setText("Untitled "+ i);
			start.setContent(createdEnvironment.get(i).getPane());
			environments.getTabs().add(start);
			start.setStyle("-fx-background-color: " + randomColor());
		}
	}
	protected Boolean getScene(){		
		return createdEnvironment.get(0).getScene();
	}
	protected Tab getTab() {
		ObservableList<Tab> itr = topbar.environment.getTabs();
		for (int i = 0; i < itr.size(); i++) {
			if (itr.get(i).isSelected()) {
				return itr.get(i);
			}
		}
		return null;
	}
	protected List<Environment> getcreatedEnvironment(){
		return createdEnvironment;
	}
	private String randomColor(){
		return "rgb("
				+ (int)(rand.nextInt(255))
				+ "," + (int)(rand.nextInt(255))
				+ "," + (int)(rand.nextInt(255))
				+ ")";
	}
}
