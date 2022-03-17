package SpaceInvaders.Panes;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

@Getter

public class HighScore extends Pane implements CustomText{
    private static ArrayList<Integer> highScoreList;
    private File file = new File("src/resources/HighScores.ser");
    private static Label label;
    private final String highScoreText = "High Score: ";


    public HighScore(){
        super();
        if(!file.exists()){
            highScoreList = new ArrayList<>();
            highScoreList.add(0);
            serialize();
        }
        deSerialize();
        label = newLabel(highScoreText+highScoreList.get(0));
        getChildren().add(label);
    }

    private void deSerialize(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            highScoreList =  (ArrayList<Integer>) ois.readObject();
        }catch (EOFException exc){
            exc.printStackTrace();
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void serialize(){
        Collections.sort(highScoreList);
        highScoreList.sort((o1, o2) -> o2 - o1);
        System.out.println(highScoreList);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(highScoreList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScore(int score){
//        if(getHighScoreList().size() <= 100) {
//            getHighScoreList().add(score);
//        }
//        boolean add = false;
//        for (Integer n : getHighScoreList()) {
//            if(score > n) {
//                add = true;
//            }
//        }
//        if(add)
            highScoreList.add(score);
            serialize();
    }
}
