package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EndScreenPane extends BorderPane {

    public EndScreenPane(GamePane gamePane, Boolean isPlayerOne){
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Font buttonBigFont = new Font("ComicSans",15* Main.SCALE);
        Button newGameButton = new Button( "Player "+ (isPlayerOne? "One":"Two") +" Won"+"\n"+"Player Score: "+
                (isPlayerOne? ScorePane.getPlayerOneScore() : ScorePane.getPlayerTwoScore()));
        newGameButton.setFont(buttonBigFont);
        newGameButton.setOnAction(e -> {
            Main.newGame(gamePane.WIDTH, gamePane.HEIGHT);
        });
        setCenter(newGameButton);
    }
}
