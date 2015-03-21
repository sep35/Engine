package layout;

import inputs.AbstractUpdate;
import javafx.scene.layout.BorderPane;

public class LayoutPane extends BorderPane{
	private AbstractLayout myLayout;
	public LayoutPane(AbstractLayout layout){
		super();
		myLayout = layout;
	}
	public void notify(AbstractUpdate in){
		myLayout.setChangedAndNotify(in);
	}
}
