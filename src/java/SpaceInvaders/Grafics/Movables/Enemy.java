package SpaceInvaders.Grafics.Movables;

import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Main;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Enemy extends MovableNonPlayer {
    private int pointValue;
    private int lives = 1;
    public Enemy(String imgPath, double posX, double posY, int gridPosX, int gridPosY, GamePane parent){
        super(imgPath, posX, posY, gridPosX, gridPosY, parent);
    }

    public void moveDown(Enemy[][] enemies){
        if(getGridPosY() == Main.TILES_Y-1){
            parentPane.getChildren().remove(this);
            parentPane.getGameUpdate().getEnemyGrid()[gridPosX][Main.TILES_Y-1] = null;
            return;
        }
        setGridPosY(getGridPosY()+1);
        setTranslateY(getTranslateY() + Main.TILE_SIZE);
        enemies[gridPosX][gridPosY] = this;
    }

    public int getHit(){
        return --lives;
    }


    @Override
    public void interact(PlayerShip player) {
            player.die();
        }
}
