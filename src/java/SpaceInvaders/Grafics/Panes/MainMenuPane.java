package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.GameUpdate;
import SpaceInvaders.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenuPane extends BorderPane {

    public MainMenuPane(int width, int height){
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Font buttonMediumFont = new Font("ComicSans",7*Main.SCALE);
        VBox gameChoices = new VBox();
        gameChoices.setSpacing(20* Main.SCALE);
        gameChoices.setPadding(new Insets(30*Main.SCALE,0,0,0));

        Button singlePlayerButton = new Button("Single-Player");
        singlePlayerButton.setOnAction(e-> Main.startGame(true));
        singlePlayerButton.setTranslateX(width/2- 25*Main.SCALE);
        singlePlayerButton.setFont(buttonMediumFont);

        Button multiPlayerButton = new Button("Multi-Player");
        multiPlayerButton.setOnAction(e-> Main.startGame(false));
        multiPlayerButton.setTranslateX(width/2 - 25*Main.SCALE);
        multiPlayerButton.setFont(buttonMediumFont);

        gameChoices.getChildren().add(singlePlayerButton);
        gameChoices.getChildren().add(multiPlayerButton);
        setCenter(gameChoices);
    }
}
