package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.Grafics.Movables.Enemy;
import javafx.geometry.Point2D;
import lombok.Getter;
import java.util.HashMap;

@Getter
public class EnemyGrid {
    HashMap<Point2D, Enemy> enemyList;
    public EnemyGrid(){
        enemyList = new HashMap<>();
    }

}
