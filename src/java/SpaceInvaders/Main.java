package SpaceInvaders;

import SpaceInvaders.Panes.GamePane;
import SpaceInvaders.Panes.MainMenuPane;
import SpaceInvaders.Panes.ScorePane;
import SpaceInvaders.Panes.StatPane;
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
    public static ArrayList<Integer> highScoreList;

        @Override
        public void start(Stage stage) {

            root = new BorderPane();
            root.setPrefSize(GAME_WIDTH*SCALE,GAME_HEIGHT*SCALE);
            Scene scene = new Scene(root);
            highScoreList = deSerializeHighScore();

            File file = new File("src/resources/HighScores.ser");
            highScoreList = null;

            if(!file.exists()){
                highScoreList = new ArrayList<>();
                serializeHighScore(0);
            } else {
                highScoreList = deSerializeHighScore();
            }

            StatPane statPane = new StatPane(highScoreList.get(0));
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
        StatPane statPane = new StatPane(highScoreList.get(0));
        root.setTop(statPane);
        root.setCenter(new GamePane(isSinglePlayer,statPane, root));
    }


    private ArrayList<Integer> deSerializeHighScore(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/resources/HighScores.ser"))){
            return (ArrayList<Integer>) ois.readObject();
        }catch (EOFException exc){
            ArrayList<Integer> highScore = new ArrayList<>();
            highScore.add(0);
            return highScore;
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }

    public static void serializeHighScore(int score){
        if(getHighScoreList().size() <= 10) {
            getHighScoreList().add(score);
        }
        boolean add = false;
        for (Integer n : getHighScoreList()) {
            if(score > n) {
                add = true;
            }
        }
        if(add)
            getHighScoreList().add(score);

        Collections.sort(getHighScoreList());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("HighScores.ser"))){
            oos.writeObject(getHighScoreList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Integer> getHighScoreList(){return highScoreList;}
}
