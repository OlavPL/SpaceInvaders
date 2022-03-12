package SpaceInvaders;

import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Grafics.Panes.ScorePane;
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

    private BorderPane root;

        @Override
        public void start(Stage stage) {

            root = new BorderPane();
            Scene scene = new Scene(root);

            GamePane gamePane = new GamePane(false, scene);
            root.setTop(new ScorePane());
            root.setCenter(gamePane);

            stage.setTitle("Space Invaders");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
}
