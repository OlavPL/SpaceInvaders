package SpaceInvaders.Grafics.Panes;

import SpaceInvaders.Main;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

interface CustomText {
    Font font = new Font("Comic Sans",3 * Main.SCALE);

    default Label newLabel(String string){
        Label label = new Label(string);
        label.setFont(font);
        label.setFont(font);
        label.setTextFill(Color.WHITE);
        return label;
    }
}
