package layout;

import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import attributes.Descriptor;
import boardObjectModels.Board;
import boardObjectModels.Patch;

public abstract class AbstractCenter extends Pane {

	protected AbstractLayout myLayout;   
	protected GridPane myVisualBoard;
	protected Pane myVisualBoardHolder;
	protected Pane myPane;
	protected Board myBoard;
	//protected int hSize;
	//protected int vSize;
	private List<Integer> myDims;
	protected ResourceBundle myConstants;
	public static final String CONSTANTS_PATH = "resources/constants";
	protected int xSize, ySize;
	protected String myTeam;


	public AbstractCenter(AbstractLayout layout){
	    
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		myLayout = layout;
		
		myPane = new FlowPane(Orientation.VERTICAL);
		myVisualBoard = new GridPane();
		
		//myVisualBoardHolder = new Pane();
		//myVisualBoardHolder.getChildren().add(myVisualBoard); 

		ScrollPane scroller = new ScrollPane();
		scroller.setMaxWidth(Integer.parseInt(myConstants.getString("Center_Pane_Width")));
		scroller.setContent(myVisualBoard);
		myPane.getChildren().add(scroller); 
		this.getChildren().add(myPane);
		myLayout.getPane().setCenter(this);
		myLayout.setChangedAndNotify(null);
	}

	public void update () {
	    myDims = myLayout.getBoard().getDims();
        xSize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Width"))))/myDims.get(0);
        ySize = (int) Math.floor((Integer.parseInt(myConstants.getString("Center_Pane_Height"))))/myDims.get(1);   
		myBoard = myLayout.getBoard();
		List<List<Patch>> patchTemp = myBoard.getPatchList();

		for (List<Patch> list : patchTemp) {
			for(Patch p : list){
				List<Integer> dimensions = p.getPosition();
				myVisualBoard.add(new Rectangle(
						xSize, 
						ySize, 
						Color.BLUE), dimensions.get(0), dimensions.get(1));

				if (!(p.getGameObject()==null)){
					boolean image = false;
					List<Descriptor> dList = p.getGameObject().getDescriptors();
					for(Descriptor d: dList){
						if(d.getName().equals("Image")){
							ImageView im = new ImageView(new Image(d.getValue()));
							im.setFitHeight(ySize);
							im.setFitWidth(xSize);
							myVisualBoard.add(im, dimensions.get(0), dimensions.get(1));
							image = true; break;
						}
					}
					if(!image) myVisualBoard.add(new Rectangle(
							xSize, 
							ySize, 
							Color.RED), myDims.get(0), myDims.get(1));
				};
			}
		}
	}
}
