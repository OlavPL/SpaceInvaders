package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StatPane extends VBox implements CustomText{
    private static int playerOneLives = 3;
    private static int playerTwoLives = 3;
    private Label playerOneLabel;
    private Label playerTwoLabel;
    private ScorePane scorepane;

    public StatPane(){
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        playerOneLabel = newLabel("Player One Lives: " + playerOneLives);
        playerTwoLabel = newLabel("Player Two Lives: " + playerOneLives);
        HBox livesBox = new HBox();
        livesBox.setSpacing( 55*Main.SCALE);
        livesBox.setPadding(new Insets(0,0,0,20*Main.SCALE));
        updateLabelLives(true);
        updateLabelLives(false);
        livesBox.getChildren().add(playerOneLabel);
        livesBox.getChildren().add(playerTwoLabel);
        this.scorepane = new ScorePane();
        getChildren().addAll(scorepane, livesBox);
    }


    public void updateLabelLives(boolean isPlayerOne){
        if(isPlayerOne) {
            playerOneLabel.setText("Player One Lives: " + playerOneLives);
            return;
        }
        playerTwoLabel.setText("Player Two Lives: " + playerTwoLives);
    }
    public void updateLabelLives(boolean isPlayerOne, int lives){
        if(isPlayerOne) {
            playerOneLives = lives;
            playerOneLabel.setText("Player One Lives: " + playerOneLives);
            return;
        }
        playerTwoLives = lives;
        playerTwoLabel.setText("Player Two Lives: " + playerTwoLives);
    }

}
