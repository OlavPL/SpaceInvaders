package SpaceInvaders.Movable;

import SpaceInvaders.Main;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bullet extends PlayerComponent {
     private double bulletSpeed = 1.25 * Main.SCALE;
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

    public boolean reachedTop(){
        return getGridPosY() == 0;
    }
    public void moveUp(){
        setTranslateY(getTranslateY()- bulletSpeed);
        updateGridPosY();
    }
}