package SpaceInvaders.Grafics.Movables;

import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Grafics.Panes.ScorePane;
import SpaceInvaders.Main;
import javafx.geometry.Point2D;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class Bullet extends PlayerComponent{
     private int bulletSpeed = 1 * Main.SCALE;
     private int gridPosX, gridPosY;
     private boolean playerOne;


    public Bullet(boolean playerOne, double posX, double posY) {
        super(playerOne ? "Sprites/playerOneBullet.png" : "Sprites/playerTwoBullet.png");
        this.playerOne = playerOne;
        setFitWidth(getImage().getWidth()/3 * Main.SCALE);
        setFitHeight(getImage().getHeight()/3 * Main.SCALE);
        setTranslateX(posX - getFitWidth()/2);
        setTranslateY(posY - getFitHeight());
        updateGridPosX();
        updateGridPosY();
    }


    public void updateGridPosX(){
        checkX(Main.TILE_SIZE * Main.TILES_X, Main.TILES_X);
    }

    public void updateGridPosY(){
        checkY(Main.TILE_SIZE * Main.TILES_Y, Main.TILES_Y);
    }

    public void checkX(double gridBreakpoint, int n){
        if(getTranslateX() > gridBreakpoint) {
            gridPosX = n;
            return;
        }
        checkX(gridBreakpoint - Main.TILE_SIZE, n-1);
    }

    public void checkY(double gridBreakpoint, int n){
        if(getTranslateY() > gridBreakpoint) {
            gridPosY = n;
            return;
        }
        checkY(gridBreakpoint - Main.TILE_SIZE, n-1);
    }
    public void moveUp(){
        setTranslateY(getTranslateY()- bulletSpeed);
        updateGridPosY();
    }
}
