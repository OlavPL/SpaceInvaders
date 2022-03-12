package SpaceInvaders.Movable;

import SpaceInvaders.Main;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlayerComponent extends ImageView {

    protected int gridPosX, gridPosY;

    public PlayerComponent(String imgPath) {
        super(imgPath);

    }

    public void updateGridPosX() {
        checkX(Main.TILE_SIZE * Main.TILES_X, Main.TILES_X);
    }

    public void updateGridPosY() {
        checkY(Main.TILE_SIZE * Main.TILES_Y, Main.TILES_Y);
    }

    public void checkX(double gridBreakpoint, int n) {
        if (getTranslateX() > gridBreakpoint) {
            gridPosX = n;
            return;
        }
        checkX(gridBreakpoint - Main.TILE_SIZE, n - 1);
    }

    public void checkY(double gridBreakpoint, int n) {
        if (getTranslateY() > gridBreakpoint) {
            gridPosY = n;
            return;
        }
        checkY(gridBreakpoint - Main.TILE_SIZE, n - 1);
    }
}
