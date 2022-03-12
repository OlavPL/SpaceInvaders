package SpaceInvaders.Grafics.Movables;

import SpaceInvaders.Grafics.Panes.GamePane;
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


    //Aner ikke hvorfor det trengs 11 og ikke 13 her..
    public boolean intersects(PlayerShip player){
        if(getGridPosY() > 11) {
            if (getTranslateX() >= player.getTranslateX() &&
                getTranslateX() <= player.getTranslateX()+player.getFitWidth() &&
                getTranslateY() >= player.getTranslateY() &&
                getTranslateY() <= player.getTranslateY() + player.getFitHeight()) {
                interact(player);
                return true;
            }
        }
        return false;
    }

    public abstract void interact(PlayerShip player);
}
