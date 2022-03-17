package SpaceInvaders.Movable;

import SpaceInvaders.Panes.GamePane;
import SpaceInvaders.Main;
import SpaceInvaders.Panes.ScorePane;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Enemy extends MovableNonPlayer {
    protected int pointValue;
    public Enemy(String imgPath, double posX, double posY, int gridPosX, int gridPosY, GamePane parent){
        super(imgPath, posX, posY, gridPosX, gridPosY, parent);
    }

    public void moveDown(Enemy[][] enemies){
        if(getGridPosY() >= Main.TILES_Y-2){
            parentPane.getChildren().remove(this);
            parentPane.getGameUpdate().getEnemyGrid()[gridPosX][gridPosY] = null;
            return;
        }
        setGridPosY(getGridPosY()+1);
        setTranslateY(getTranslateY() + Main.TILE_SIZE);
        enemies[gridPosX][gridPosY] = this;
    }

    public boolean isHit(){
        return true;
    }
}
