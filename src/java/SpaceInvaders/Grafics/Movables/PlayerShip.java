package SpaceInvaders.Grafics.Movables;

import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter

public class PlayerShip extends PlayerComponent {
    private GamePane gamePane;
    private boolean playerOne;
    private boolean shootOnCoolDown = false;
    private long shootCoolDownInMillis = 500;
    private Timeline shootCoolDown;
    private int movementSpeed = 3;
    private boolean alive = true;

    public PlayerShip(GamePane gamePane, boolean playerOne, int width, int height) {
        super(playerOne ? "Sprites/Blue_Spaceship.png" : "Sprites/Red_Spaceship.png");
        this.gamePane = gamePane;
        this.playerOne = playerOne;
        initShootCoolDown();
        setTranslateX(playerOne ? width / 3: width - width / 3);
        setTranslateY(height - height / 10 - getFitHeight() / 2);
        setFitWidth(getImage().getWidth()/3 * Main.SCALE);
        setFitHeight(getImage().getHeight()/3 * Main.SCALE);
        setLayoutX(-getFitWidth()/2);
        setLayoutY(-getFitHeight()/2);
    }

    public void moveLeft(){
        if(getTranslateX() > 0 + getFitWidth() / 2){
            setTranslateX(getTranslateX() - movementSpeed);
        }
    }

    public void moveRight(){
        if(getTranslateX() < gamePane.WIDTH - getFitWidth()/2){
            setTranslateX(getTranslateX() + movementSpeed);
        }
    }

    public void die(){
        gamePane.getChildren().remove(this);
        setAlive(false);

    }
    public void shoot(ArrayList<Bullet> playerBullet){
        Bullet bullet = new Bullet(playerOne, getTranslateX(), getTranslateY());
        gamePane.getChildren().add(bullet);
        playerBullet.add(bullet);
        shootOnCoolDown = true;
    }

    private void initShootCoolDown(){
        shootCoolDown = new Timeline( new KeyFrame(
                Duration.millis(300),
                e->{shootOnCoolDown = false;})
        );
        shootCoolDown.setCycleCount(1);
    }
}
