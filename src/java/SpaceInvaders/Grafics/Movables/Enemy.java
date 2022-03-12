package SpaceInvaders.Grafics.Movables;

import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Main;
import javafx.geometry.Point2D;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;

@Setter
@Getter

public class Enemy extends MovableNonPlayer {
    private int pointValue;
    private int lives = 1;
    public Enemy(String imgPath, double posX, double posY, int gridPosX, int gridPosY, GamePane parent){
        super(imgPath, posX, posY, gridPosX, gridPosY, parent);
    }

    public void moveDown(HashMap<Point2D, Enemy> enemies){
        setGridPosY(getGridPosY()+1);
        if(getGridPosY() == 16){
            parentPane.getChildren().remove(this);
            parentPane.getGameUpdate().getEnemyGrid().remove(new Point2D(gridPosX, gridPosY));
            return;
        }
        setTranslateY(getTranslateY() + Main.TILE_SIZE);
        Point2D newGridPos = new Point2D(gridPosX, gridPosY);
        if(enemies.get(newGridPos)!= null) {
            enemies.replace(newGridPos, this);
            return;
        }
        enemies.put(newGridPos, this);
    }

    public int getHit(){
        return --lives;
    }


    @Override
    public void interact(PlayerShip player) {
            player.die();
        }
}
