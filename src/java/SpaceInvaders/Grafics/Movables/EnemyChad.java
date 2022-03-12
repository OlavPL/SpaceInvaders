package SpaceInvaders.Grafics.Movables;

import SpaceInvaders.Grafics.Panes.GamePane;

public class EnemyChad extends Enemy{
    public EnemyChad(double posX, double posY, int gridPosX, int gridPosY, GamePane parentPane) {
        super("Sprites/Chad.png", posX, posY, gridPosX, gridPosY, parentPane);
        setLives(2);
    }
}
