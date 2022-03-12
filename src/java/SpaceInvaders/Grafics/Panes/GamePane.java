package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.GameUpdate;
import SpaceInvaders.Grafics.Movables.PlayerShip;
import SpaceInvaders.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.Getter;

@Getter

public class GamePane extends Pane {
    public final int WIDTH;
    public final int HEIGHT;
    private PlayerShip playerOne;
    private PlayerShip playerTwo;
    private GameUpdate gameUpdate;

    public GamePane (boolean onePlayer, Scene scene){
        super();
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        WIDTH = Main.GAME_WIDTH * Main.SCALE;
        HEIGHT = Main.GAME_HEIGHT * Main.SCALE;
        setPrefSize(WIDTH, HEIGHT);

        addPlayers(onePlayer);
        addGameUpdate(onePlayer, scene);

    }

    private void addPlayers(boolean onePlayer){
        if(onePlayer){
            playerOne = new PlayerShip(this, true, WIDTH, HEIGHT);
            getChildren().add(playerOne);
        }
        else{
            playerOne = new PlayerShip(this,true, WIDTH, HEIGHT);
            playerTwo = new PlayerShip(this,false, WIDTH, HEIGHT);

            getChildren().addAll(playerOne, playerTwo);
        }
    }

    private void addGameUpdate(boolean onePlayer, Scene scene){
        if(onePlayer){
            gameUpdate = new GameUpdate(this, playerOne, scene);
        }
        else{
            gameUpdate = new GameUpdate(this, playerOne, playerTwo, scene);
        }
    }
}
