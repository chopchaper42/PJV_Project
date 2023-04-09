package Engine.Tiles;

import Engine.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Wall extends Tile
{
    private Image image = new Image(new File("./src/main/assets/wall.png").toURI().toString());

    public Wall(double x, double y) {
        super(x, y);
    }

    public Wall(Point2D position) {
        super(position);
    }

    public Wall(Point2D position, Item item) {
        super(position, item);
    }

    public Wall(double x, double y, Item item) {
        super(x, y, item);
    }

    public Image getImage()
    {
        return image;
    }
}
