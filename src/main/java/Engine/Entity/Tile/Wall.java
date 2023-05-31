package Engine.Entity.Tile;

import Engine.Entity.Items.Item;
import javafx.scene.image.Image;

import java.io.File;

public class Wall extends Tile {
    public static final Image WALL_IMG = new Image(new File("./src/main/assets/wall.png").toURI().toString());

    /**
     * Creates a wall
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Wall(double x, double y) {
        super(WALL_IMG, x, y, true);
    }
}
