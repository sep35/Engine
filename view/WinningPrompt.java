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

public class WinningPrompt {
    private ResourceBundle myConstants;
    private static final String CONSTANTS_PATH = "resources/constants";
    private int DEFAULT_STAGE_WIDTH;
    private int DEFAULT_STAGE_HEIGHT;

    public WinningPrompt(String s) {
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

        Label textLabel = new Label("Congratulations!\nTeam "+s+" has won this level."
                + "/nPress enter to play the next level!");

       
        Button enter = new Button("Enter");

        GridPane.setHalignment(textLabel, HPos.RIGHT);

        gridPane.add(textLabel, 0, 0);
        gridPane.add(enter, 0, 2);

        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                    dialogStage.close();
            }
        });
        dialogStage.setTitle("Level over!");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();;
    }

}