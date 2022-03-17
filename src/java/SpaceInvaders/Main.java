package SpaceInvaders;

import SpaceInvaders.Panes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    public final static int SCALE = 6;
    public final static int GAME_WIDTH = 150;
    public final static int GAME_HEIGHT = 150;
    public final static int TILES_X = 15;
    public final static int TILES_Y = 15;
    public final static int TILE_SIZE = 10*SCALE;

    private static BorderPane root;

        @Override
        public void start(Stage stage) {

            root = new BorderPane();
            root.setPrefSize(GAME_WIDTH*SCALE,GAME_HEIGHT*SCALE);
            Scene scene = new Scene(root);
            StatPane statPane = new StatPane();
            root.setTop(statPane);
            root.setCenter(new MainMenuPane(GAME_WIDTH*SCALE, GAME_HEIGHT*SCALE));

            stage.setTitle("Space Invaders");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }

        public static void newGame(int width, int height){
            root.setCenter(new MainMenuPane(width, height));
        }

    public static void startGame(boolean isSinglePlayer){
        ScorePane.setPlayerOneScore(0);
        ScorePane.setPlayerTwoScore(0);
        StatPane statPane = new StatPane();
        root.setTop(statPane);
        root.setCenter(new GamePane(isSinglePlayer,statPane, root));
    }
}
