package layout;

import gameObjectModel.GameObject;

import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import util.FolderList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AuthoringLeft extends AbstractLayout {
	private AbstractLayout myLayout;
	private static BankTab imageBank;
	private static Set images;
	protected ResourceBundle myConstants;
    public static final String CONSTANTS_PATH = "resources/constants";    
    private TabPane myTabPane;
	
	
	public AuthoringLeft(AuthoringLayout layout) {
	    myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
	    myTabPane = new TabPane();
		myTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myLayout = layout;
		myTabPane.setMaxWidth(Integer.parseInt(myConstants.getString("Left_Pane_Width")));
		myTabPane.setMaxHeight(Integer.parseInt(myConstants.getString("Left_Pane_Height")));
		myLayout.getPane().setLeft(myTabPane);
	}
	
	public void initializeImages(){
		FolderList folders = new FolderList();
		List<String> imageLocations = folders.getContents("src//images");
		for(String loc : imageLocations){
			ImageView img = new ImageView(new Image("/images/" + loc));
			newImage(img);
		}
	}
	
	public void newImage(ImageView img){
		images = new HashSet<ImageView>();
		images.add(img);
		imageBank.addImage(img);
	}

	@Override
	void updateImages(ImageView imageView) {
		// TODO Auto-generated method stub
		
	}
	protected void addTabs(List<BankTab> tabs){
	    for(BankTab t: tabs){
	    	t.setText(t.getName());
	        myTabPane.getTabs().add(t);
	    }
	}



    @Override
    void updateViewRight (GameObject g, List<Integer> position) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void sendBoardInfo (String s, List<Integer> position) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void setWinningTeam (String s) {
        // TODO Auto-generated method stub
        
    }
}
