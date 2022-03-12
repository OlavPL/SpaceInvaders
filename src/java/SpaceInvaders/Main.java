package SpaceInvaders;

import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Grafics.Panes.MainMenuPane;
import SpaceInvaders.Grafics.Panes.ScorePane;
import SpaceInvaders.Grafics.Panes.StatPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
            Scene scene = new Scene(root);

            StatPane statPane = new StatPane();
            GamePane gamePane = new GamePane(false, statPane, root);
            root.setTop(statPane);
            root.setCenter(gamePane);

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
        StatPane statPane = new StatPane();
        root.setTop(statPane);
        root.setCenter(new GamePane(isSinglePlayer,statPane, root));
    }
}
