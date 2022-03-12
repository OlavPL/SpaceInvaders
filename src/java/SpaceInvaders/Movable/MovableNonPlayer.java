package SpaceInvaders.Movable;

import SpaceInvaders.Panes.GamePane;
import SpaceInvaders.Main;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class MovableNonPlayer extends ImageView {
    public static final double OFFSET_X = Main.TILE_SIZE * .05;
    public static final double OFFSET_Y = Main.TILE_SIZE * .09;
    protected int gridPosX;
    protected int gridPosY;
    protected GamePane parentPane;

    public MovableNonPlayer(String imgPath, double posX, double posY, int gridPosX, int gridPosY, GamePane parent){
        super(imgPath);
        this.gridPosX = gridPosX;
        this.gridPosY = gridPosY;
        this.parentPane = parent;
        setTranslateX(posX);
        setTranslateY(posY);
        setFitWidth(Main.TILE_SIZE * 0.9);
        setFitHeight(Main.TILE_SIZE * 0.82);
    }


    public boolean intersects(PlayerShip player){
        if(getGridPosY() > 11) {
            return getTranslateX() >= player.getTranslateX() &&
                    getTranslateX() <= player.getTranslateX() + player.getFitWidth() &&
                    getTranslateY() >= player.getTranslateY() &&
                    getTranslateY() <= player.getTranslateY() + player.getFitHeight();
        }
        return false;
    }
}
