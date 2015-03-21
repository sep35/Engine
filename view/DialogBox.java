package view;

import java.util.*;

import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DialogBox {
	private ResourceBundle myConstants;
	private static final String CONSTANTS_PATH = "resources/constants";
	private int DEFAULT_STAGE_WIDTH;
	private int DEFAULT_STAGE_HEIGHT;
	private int xSize, ySize, teams, moves;

	public DialogBox() {
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		DEFAULT_STAGE_WIDTH = Integer.parseInt(myConstants.getString("Default_StageW"));
		DEFAULT_STAGE_HEIGHT = Integer.parseInt(myConstants.getString("Default_StageH"));
		
		Stage dialogStage = new Stage();
		GridPane gridPane = new GridPane();
		gridPane.setStyle("-fx-background-color: Lightblue");
		gridPane.setPadding(new Insets(40, 0, 0, 50));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		Scene scene = new Scene(gridPane, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);

		Label xLabel = new Label("Give dimension for width");
		Label yLabel = new Label("Give dimension for height");
		Label moveLabel = new Label("Number of moves per turn");

		TextField xDim = new TextField();
		TextField yDim = new TextField();
		TextField numMoves = new TextField();

		Button enter = new Button("Enter");

		GridPane.setHalignment(xLabel, HPos.RIGHT);
		GridPane.setHalignment(yLabel, HPos.RIGHT);
		GridPane.setHalignment(moveLabel, HPos.RIGHT);

		gridPane.add(xLabel, 0, 0);
		gridPane.add(xDim, 1, 0);
		gridPane.add(yLabel, 0, 1);
		gridPane.add(yDim, 1, 1);
		gridPane.add(moveLabel, 0, 2);
		gridPane.add(numMoves, 1, 2);
		gridPane.add(enter, 1, 4);
		
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					xSize = Integer.parseInt(xDim.getText());
					ySize = Integer.parseInt(yDim.getText());
					moves = Integer.parseInt(numMoves.getText());
				} catch (NumberFormatException nfe){
					xSize = 20;
					ySize = 20;
					moves = 2;
				}

				dialogStage.close();
			}
		});
		dialogStage.setTitle("Game Conditions");
		dialogStage.setScene(scene);
		dialogStage.showAndWait();;
	}

	public int getXSize(){
		return xSize;
	}

	public int getYSize(){
		return ySize;
	}

	public int getNumTeams(){
		return teams;
	}

	public int getNumMoves(){
		return moves;
	}
}