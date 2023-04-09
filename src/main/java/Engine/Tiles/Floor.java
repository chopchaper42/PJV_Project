package Engine.Tiles;

import Engine.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Floor extends Tile
{
    private Image image = new Image(new File("./src/main/assets/floor.png").toURI().toString());
    public Floor(double x, double y) {
        super(x, y);
    }

    public Floor(Point2D position) {
        super(position);
    }

    public Floor(Point2D position, Item item) {
        super(position, item);
    }

    public Floor(double x, double y, Item item) {
        super(x, y, item);
    }

    public Image getImage()
    {
        return image;
    }
}


