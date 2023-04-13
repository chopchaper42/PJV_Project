package Engine.Entity.Tiles;

import javafx.scene.image.Image;

import java.io.File;

public class Wall extends Tile
{
    private static final Image image = new Image(new File("./src/main/assets/wall.png").toURI().toString());

    /**
     * Creates a wall
     * @param x x coordinate
     * @param y y coordinate
     */
    public Wall(double x, double y) {
        super(image, x, y);
    }
}
