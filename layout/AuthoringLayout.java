package layout;

import gameManager.Victory;
import gameObjectModel.GameObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import boardObjectModels.ObservableBoardHolder;
import boardObjectModels.Output;
import javafx.scene.image.ImageView;

public class AuthoringLayout extends AbstractLayout{
	private AuthoringLeft leftside;
	private List<String> myTabNames;
	private Map<String, BankTab> myTabs;
	private Random rand;
	
	public AuthoringLayout(ObservableBoardHolder board){
		rand = new Random();
		updateBoard(board);
		myPane = new LayoutPane(this);
		new AuthoringTop(this);
		new AuthoringCenter(this);
		AuthoringLeft left = new AuthoringLeft(this);
		new AuthoringRight(this);		
		new AuthoringBottom(this);
		myTabNames = Arrays.asList("GameObject", "PassiveAttribute", "ActiveAttribute", "Team", "ImageDescriptor");
		myTabs = new HashMap<String,BankTab>();
		for(String s: myTabNames) myTabs.put(s,new BankTab(s, this));
		left.addTabs(Arrays.asList(myTabs.get("Team"),
				myTabs.get("GameObject"),
				myTabs.get("ImageDescriptor"),
				myTabs.get("PassiveAttribute"),
				myTabs.get("ActiveAttribute")));
	}
	
	public void updateImages(ImageView img) {
		leftside.newImage(img);
	}
	
	 @Override
	    public void update (Observable o, Object arg) {
	        // TODO Render board for view by user
	     Output out = (Output) arg;
         List<String> changedList = out.getElements();
         Victory v = out.getVictory();
	        for(String s : changedList){
	        	myTabs.get(s).setStyle("-fx-background-color: " + randomColor());
	            if(myTabs.containsKey(s)) myTabs.get(s).update(myBoardHolder.getMapping(s));
	        }
	        AbstractCenter p = (AbstractCenter)myPane.getCenter();
	          p.update();
	    }
	private String randomColor(){
		return "rgb("
				+ (int)(rand.nextInt(255))
				+ "," + (int)(rand.nextInt(255))
				+ "," + (int)(rand.nextInt(255))
				+ ")";
	}
    @Override
    void updateViewRight (GameObject g, List<Integer> position) {
        AuthoringRight right = (AuthoringRight) myPane.getRight();
        right.updateItem(g, position);
    }

    @Override
    void sendBoardInfo (String s, List<Integer> position) {
        PlayingCenter center = (PlayingCenter) myPane.getCenter();
        center.updateItem(s, position);
    }

    @Override
    void setWinningTeam (String s) {
       // This should do nothing
    }
}
