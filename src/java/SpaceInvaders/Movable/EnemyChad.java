package SpaceInvaders.Movable;


import SpaceInvaders.Panes.GamePane;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EnemyChad extends Enemy{
    public EnemyChad(double posX, double posY, int gridPosX, int gridPosY, GamePane parentPane) {
        super("Sprites/Chad.png", posX, posY, gridPosX, gridPosY, parentPane);
        setPointValue(50);
    }
}
