package SpaceInvaders;

import SpaceInvaders.Grafics.Movables.*;
import SpaceInvaders.Grafics.Panes.GamePane;
import SpaceInvaders.Grafics.Panes.ScorePane;
import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Getter

public class GameUpdate{
    private boolean onePlayer = true;
    private PlayerShip playerOne;
    private PlayerShip playerTwo;
    private GamePane gamePane;
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private final Enemy[][] enemyGrid = new Enemy[15][15];
    private ArrayList<Bullet> playerOneBullets;
    private ArrayList<Bullet> playerTwoBullets;

    public GameUpdate(GamePane gamePane, PlayerShip playerOne, Scene scene){
        super();
        this.gamePane = gamePane;
        this.playerOne = playerOne;
        this.playerOneBullets = new ArrayList<>();
        setListenerOnScene(scene);
        initUpdateTimer();
        setEnemyTimer();

    }
    public GameUpdate(GamePane gamePane, PlayerShip playerOne, PlayerShip playerTwo, Scene scene){
        this(gamePane, playerOne, scene);
        this.playerTwo = playerTwo;
        this.playerTwoBullets = new ArrayList<>();
        onePlayer = false;
    }

    private void handleMovement(boolean onePlayer){
        if(playerOne.isAlive()) {
            if (keyPressed(KeyCode.A)) {
                playerOne.moveLeft();
            }
            if (keyPressed(KeyCode.D)) {
                playerOne.moveRight();
            }
            if (keyPressed(KeyCode.W)) {
                if (!playerOne.isShootOnCoolDown()) {
                    playerOne.shoot(playerOneBullets);
                    playerOne.getShootCoolDown().play();
                }
            }
        }

        if(!onePlayer) {
            if(playerTwo.isAlive()) {
                if (keyPressed(KeyCode.LEFT)) {
                    playerTwo.moveLeft();
                }
                if (keyPressed(KeyCode.RIGHT)) {
                    playerTwo.moveRight();
                }
                if (keyPressed(KeyCode.UP)) {
                    if (!playerTwo.isShootOnCoolDown()) {
                        playerTwo.shoot(playerTwoBullets);
                        playerTwo.getShootCoolDown().play();
                    }
                }
            }
            else if(!playerOne.isAlive())
            {
                gameOver();
            }
        }
    }

    private boolean keyPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    public void setListenerOnScene(Scene scene){
        scene.setOnKeyPressed(e -> {
            keys.put(e.getCode(), true);
        });
        scene.setOnKeyReleased(e -> {
            keys.put(e.getCode(), false);

        });
    }

    private void initUpdateTimer(){
        Timeline updateTimer = new Timeline(new KeyFrame(
                Duration.millis(16),
                e -> {
                    handleMovement(onePlayer);
                    moveBullets();
                })
        );
        updateTimer.setCycleCount(Animation.INDEFINITE);
        updateTimer.play();
    }

    private void setEnemyTimer(){
        Timeline enemyTimer = new Timeline(new KeyFrame(
                Duration.millis(500),
                e -> {
                    progressWave();
                })
        );
        enemyTimer.setCycleCount(Animation.INDEFINITE);
        enemyTimer.play();
    }

    private void moveBullets(){
        ArrayList<Bullet> removedBulletsOne = new ArrayList<>();
        ArrayList<Bullet> removedBulletsTwo = new ArrayList<>();
        for (Bullet bullet: playerOneBullets) {
            bullet.moveUp();
            if(bulletkillCheck(bullet))
                removedBulletsOne.add(bullet);
        }
        for (Bullet bullet: playerTwoBullets) {
            bullet.moveUp();
            if(bulletkillCheck(bullet))
                removedBulletsTwo.add(bullet);
        }
        gamePane.getChildren().removeAll(removedBulletsOne);
        gamePane.getChildren().removeAll(removedBulletsTwo);
        playerOneBullets.removeAll(removedBulletsOne);
        playerTwoBullets.removeAll(removedBulletsTwo);
    }

    private boolean bulletkillCheck(Bullet bullet){
        Enemy enemy;
        if( (enemy = enemyGrid[bullet.getGridPosX()][bullet.getGridPosY()]) != null ) {
            if (bullet.getBoundsInLocal().intersects(enemy.getBoundsInLocal())) {
                if (enemy.getHit() == 0) {
                    System.out.println(enemy.getTranslateX()+ ", "+ bullet.getTranslateX());
                    ScorePane.updateScores(bullet.isPlayerOne(), enemy.getPointValue());
                }
                gamePane.getChildren().remove(enemy);
                enemyGrid[enemy.getGridPosX()][enemy.getGridPosY()] = null;
                return true;
            }
        }
        return false;
    }

    private void progressWave(){
        Enemy enemy;
            for (int y = Main.TILES_Y-1; y >= 0 ; y--) {
                for (int x = Main.TILES_X-1; x >= 0; x--) {
                    if( (enemy = enemyGrid[x][y]) != null) {
                        if(enemy.intersects(playerOne))
                        if(!onePlayer) {
                            enemy.intersects(playerTwo);
                        }
                        enemy.moveDown(enemyGrid);
                    }
                }
            }
        newWave();
    }

    public void newWave(){
        Random random = new Random();
        for (int i = 0; i < Main.TILES_X; i++) {
            switch (random.nextInt(4)) {

                case 0, 1, 2 -> {
                    EnemyPeon peon = new EnemyPeon(
                            i * Main.TILE_SIZE + MovableNonPlayer.OFFSET_X,
                            0 + MovableNonPlayer.OFFSET_Y,
                            i,
                            0,
                            gamePane
                    );
                    enemyGrid[i][0] = peon;
                    gamePane.getChildren().add(peon);
                }

                case 3 -> {
                    EnemyChad chad = new EnemyChad(i * Main.TILE_SIZE + MovableNonPlayer.OFFSET_X,
                            0 + MovableNonPlayer.OFFSET_Y,
                            i,
                            0,
                            gamePane
                    );
                    enemyGrid[i][0] = chad;
                    gamePane.getChildren().add(chad);
                }
            }
        }
    }
    public void gameOver(){
        System.out.println("Game Over");
    }
}
