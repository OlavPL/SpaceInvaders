package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ScorePane extends HBox implements CustomText{
    private static int playerOneScore = 0;
    private static int playerTwoScore = 0;
    private static Label playerOneLabel;
    private static Label playerTwoLabel;
    public ScorePane(){
        super();
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        playerOneLabel = newLabel("Player One Score: " + playerOneScore);
        Label highScore = newLabel("High Score: " + 9001);
        playerTwoLabel = newLabel("Player Two Score: " + playerTwoScore);
        getChildren().addAll(playerOneLabel, highScore, playerTwoLabel);
        setSpacing(16* Main.SCALE);
        setMargin(playerOneLabel, new Insets(0,0 ,0,20*Main.SCALE));
    }

    public static void updateScores(boolean playerOne, int points){
        if(playerOne){
            playerOneScore += points;
            playerOneLabel.setText("Player One Score: " + playerOneScore);
            return;
        }

        playerTwoScore += points;
        playerTwoLabel.setText("Player One Score: " + playerTwoScore);
    }

    public static int getPlayerOneScore(){return playerOneScore;}
    public static int getPlayerTwoScore(){return playerTwoScore;}
    public static boolean getWinner(){
        return playerOneScore > playerTwoScore;
    }
}
