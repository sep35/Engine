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

public class TeamPrompt {
    private ResourceBundle myConstants;
    private static final String CONSTANTS_PATH = "resources/constants";
    private int DEFAULT_STAGE_WIDTH;
    private int DEFAULT_STAGE_HEIGHT;
    private String ID;

    public TeamPrompt() {
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

        Label idLabel = new Label("Team ID:");

        TextField idField = new TextField();
        Button enter = new Button("Enter");

        GridPane.setHalignment(idLabel, HPos.RIGHT);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(enter, 1, 1);

        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                    ID = idField.getText();
                    dialogStage.close();
            }
        });
        dialogStage.setTitle("Add Team ID");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();;
    }

    public String getID(){
        return ID;
    }
}