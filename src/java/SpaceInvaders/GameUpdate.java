package SpaceInvaders;

import SpaceInvaders.Movable.*;
import SpaceInvaders.Panes.EndScreenPane;
import SpaceInvaders.Panes.GamePane;
import SpaceInvaders.Panes.HighScore;
import SpaceInvaders.Panes.ScorePane;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import lombok.Getter;

import java.io.*;
import java.util.*;

@Getter

public class GameUpdate{
    private GamePane gamePane;
    private boolean onePlayer = true;
    private PlayerShip playerOne;
    private PlayerShip playerTwo;
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Enemy[][] enemyGrid = new Enemy[15][15];
    private ArrayList<Bullet> playerOneBullets;
    private ArrayList<Bullet> playerTwoBullets;
    Timeline playerOneTimer;
    Timeline playerTwoTimer;
    Timeline bulletTimer;
    Timeline enemyTimer;


    public GameUpdate(GamePane gamePane, PlayerShip playerOne, Scene scene){
        super();
        this.gamePane = gamePane;
        this.playerOne = playerOne;
        this.playerOneBullets = new ArrayList<>();
        setEnemyTimer();
        setListenerOnScene(scene);
        setUpdateOneTimer();
        setBulletTimer();



    }
    public GameUpdate(GamePane gamePane, PlayerShip playerOne, PlayerShip playerTwo, Scene scene){
        this(gamePane, playerOne, scene);
        this.playerTwo = playerTwo;
        this.playerTwoBullets = new ArrayList<>();
        onePlayer = false;
        setUpdateTwoTimer();
    }

    private void handleOneMovement(){
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
    private void handleTwoMovement(){
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

    private void setUpdateOneTimer(){
        playerOneTimer = new Timeline(new KeyFrame(
                Duration.millis(16),
                e -> {
                    handleOneMovement();
                    playerIntersectsEnemy(playerOne);
                })
        );
        playerOneTimer.setCycleCount(Animation.INDEFINITE);
        playerOneTimer.play();
    }

    private void setUpdateTwoTimer(){
        playerTwoTimer = new Timeline(new KeyFrame(
                Duration.millis(16),
                e -> {
                    handleTwoMovement();
                    playerIntersectsEnemy(playerTwo);
                })
        );
        playerTwoTimer.setCycleCount(Animation.INDEFINITE);
        playerTwoTimer.play();
    }
    private void setBulletTimer(){
        bulletTimer = new Timeline(new KeyFrame(
                Duration.millis(16),
                e -> {
                    moveBullets();
                })
        );
        bulletTimer.setCycleCount(Animation.INDEFINITE);
        bulletTimer.play();
    }

    private void setEnemyTimer(){
        enemyTimer = new Timeline(new KeyFrame(
                Duration.millis(2000),
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
            if(bullet.reachedTop()) {
                removedBulletsOne.add(bullet);
                continue;
            }
            bullet.moveUp();
            if(bullet.bulletkillCheck(enemyGrid, gamePane)) {
                removedBulletsOne.add(bullet);
            }
        }
        if(!onePlayer){
        for (Bullet bullet: playerTwoBullets) {
            if (bullet.reachedTop()) {
                removedBulletsTwo.add(bullet);
                continue;
            }
            bullet.moveUp();
            if (bullet.bulletkillCheck(enemyGrid, gamePane))
                removedBulletsTwo.add(bullet);
        }
        }
        gamePane.getChildren().removeAll(removedBulletsOne);
        playerOneBullets.removeAll(removedBulletsOne);
        if(!onePlayer) {
            gamePane.getChildren().removeAll(removedBulletsTwo);
            playerTwoBullets.removeAll(removedBulletsTwo);
        }
    }

    private void progressWave(){
        Enemy enemy;
            for (int y = Main.TILES_Y-1; y >= 0 ; y--) {
                for (int x = Main.TILES_X-1; x >= 0; x--) {
                    if( (enemy = enemyGrid[x][y]) != null) {
                        enemy.moveDown(enemyGrid);
                    }
                }
            }
        newWave();
    }
    private boolean playerIntersectsEnemy(PlayerShip player){
        Enemy enemy;
        if( (enemy = enemyGrid[player.getGridPosX()][player.getGridPosY()]) != null) {
            boolean isPlayerOne = player.equals(playerOne);
                gamePane.getStatPane().updateLabelLives(player.equals(playerOne), player.die());
                if(player.getLives()==0) {
                    stopAllTimers();
                    ScorePane.getHighScoreList().addScore(Math.max(ScorePane.getPlayerOneScore(), ScorePane.getPlayerTwoScore()));
                    gameOver();
                }
                endOfRound(isPlayerOne);
                enemyTimer.stop();
                return true;
        }
        return false;
    }

    public void newWave(){
        Random random = new Random();
        for (int i = 0; i < Main.TILES_X; i++) {
            int n = random.nextInt(30);

            if(n == 0){
                TankBoi boi = new TankBoi(i * Main.TILE_SIZE + MovableNonPlayer.OFFSET_X,
                        0 + MovableNonPlayer.OFFSET_Y,
                        i,
                        0,
                        gamePane
                );
                enemyGrid[i][0] = boi;
                gamePane.getChildren().add(boi);
            }
            else if(n < 10){
                EnemyChad chad = new EnemyChad(i * Main.TILE_SIZE + MovableNonPlayer.OFFSET_X,
                        0 + MovableNonPlayer.OFFSET_Y,
                        i,
                        0,
                        gamePane
                );
                enemyGrid[i][0] = chad;
                gamePane.getChildren().add(chad);
            }
            else{
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
        }
    }

    public void endOfRound(boolean playerOne){
        enemyTimer.stop();
        enemyGrid = new Enemy[Main.TILES_X][Main.TILES_Y];
        playerOneTimer.stop();
        if(!onePlayer)
            playerTwoTimer.stop();
        gamePane.restartGameLoopBtn(playerOne);
        gamePane.getChildren().add(gamePane.getRestartButtonPane());

    }

    public void gameOver(){
        boolean isPlayerOneWinner = ScorePane.getWinner();
        gamePane.getParentPane().setCenter(new EndScreenPane(gamePane, isPlayerOneWinner));

    }

    public void startAllTimers(){
        enemyTimer.play();
        playerOneTimer.play();
        if(!onePlayer)
            playerTwoTimer.play();
        bulletTimer.play();
    }
    public void stopAllTimers(){
        enemyTimer.stop();
        playerOneTimer.stop();
        if(!onePlayer)
            playerTwoTimer.stop();
        bulletTimer.stop();
    }

}
