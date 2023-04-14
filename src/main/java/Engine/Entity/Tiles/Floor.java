package Engine.Entity.Tiles;

import Engine.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Floor extends Tile
{
    private Item item;
    private static Image image = new Image(new File("./src/main/assets/floor.png").toURI().toString());

    /**
     * Creates a floor tile
     * @param x x coordinate
     * @param y y coordiante
     */
    public Floor(double x, double y) {
        super(image, x, y);
    }

    /**
     * Creates a floor tile with an item
     * @param x x coordinate
     * @param y y coordinate
     * @param item item
     */
    public Floor(double x, double y, Item item) {
        super(image, x, y);
        this.item = item;
    }
}


