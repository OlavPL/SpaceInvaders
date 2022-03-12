package SpaceInvaders.Movable;

import SpaceInvaders.Panes.GamePane;

public class EnemyPeon extends Enemy{

    public EnemyPeon(double posX, double posY, int gridPosX, int gridPosY, GamePane parentPane) {
        super("Sprites/Peon.png", posX, posY, gridPosX, gridPosY, parentPane);
        setPointValue(25);
    }
}
