package Engine.Tiles;

import Engine.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Tile
{
    public static final int TILE_SIZE = 64;
    private Image image;
    Item item;
    Point2D position;

    public Tile(Point2D position) {
        this.position = position;
    }

    public Tile(double x, double y) {
        this.position = new Point2D(x, y);
    }

    public Tile(Point2D position, Item item) {
        this(position);
        this.item = item;
    }

    public Tile(double x, double y, Item item) {
        this(x, y);
        this.item = item;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public Point2D getPosition()
    {
        return position;
    }

    public Image getImage()
    {
        return image;
    }
}
