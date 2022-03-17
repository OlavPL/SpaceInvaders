package SpaceInvaders.Movable;

import SpaceInvaders.Panes.GamePane;

public class TankBoi extends Enemy{
    public TankBoi(double posX, double posY, int gridPosX, int gridPosY, GamePane parent) {
        super("Sprites/TankBoi.png", posX, posY, gridPosX, gridPosY, parent);
    }

    @Override
    public boolean isHit(){
        return false;
    }
}
