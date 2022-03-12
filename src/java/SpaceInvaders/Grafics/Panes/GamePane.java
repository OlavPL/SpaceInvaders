package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.GameUpdate;
import SpaceInvaders.Grafics.Movables.PlayerShip;
import SpaceInvaders.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;
@Getter

public class GamePane extends Pane {
    public final int WIDTH;
    public final int HEIGHT;
    private final boolean onePlayer;
    private PlayerShip playerOne;
    private PlayerShip playerTwo;
    private GameUpdate gameUpdate;
    private StatPane statPane;
    private BorderPane parentPane;
    private BorderPane restartButtonPane = new BorderPane();
    public Button restartButton;

    public GamePane (boolean onePlayer, StatPane statPane, BorderPane parent){
        super();
        this.parentPane = parent;
        this.onePlayer = onePlayer;
        this.statPane = statPane;
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        WIDTH = Main.GAME_WIDTH * Main.SCALE;
        HEIGHT = Main.GAME_HEIGHT * Main.SCALE;
        restartButtonPane.setPrefSize(WIDTH,HEIGHT);
        setPrefSize(WIDTH, HEIGHT);
        addRestartGameLoopBtn(true);


        startGame(onePlayer);
        addGameUpdate(onePlayer, parent.getScene());

    }

    private void startGame(boolean onePlayer){
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
    private void addPlayers(boolean onePlayer){
        playerOne.setTranslateX(WIDTH / 3);
        playerOne.setTranslateY(HEIGHT - HEIGHT / 10 - playerOne.getFitHeight() / 2);
        getChildren().add(playerOne);
        if(!onePlayer){
            playerTwo.setTranslateX(WIDTH - WIDTH / 3);
            playerOne.setTranslateY(HEIGHT - HEIGHT / 10 - playerOne.getFitHeight() / 2);
            getChildren().add(playerTwo);
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

    public void addRestartGameLoopBtn(boolean playerOne){
        Font buttonBigFont = new Font("ComicSans",15*Main.SCALE);
        restartButton = new Button( "Player "+ (playerOne? "One":"Two") +" Died"+"\n"+"Start Next Round");
        restartButton.setFont(buttonBigFont);
        restartButton.setOnAction(e -> {
            getChildren().clear();
            addPlayers(onePlayer);
            gameUpdate.startAllTimers();
        });
        restartButtonPane.setCenter(restartButton);
    }


    public void restartGameLoopBtn(boolean playerOne){
        restartButton.setText(( "Player "+ (playerOne? "One":"Two") +" Died"+"\n"+"Start Next Round"));
    }
}
