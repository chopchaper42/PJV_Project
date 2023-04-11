package Engine;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;

public abstract class Tile extends Entity
{
    public static final int TILE_SIZE = 64;
    private Item item;
    public Tile(Point2D position)
    {
        super(position);
    }

    public Tile(double x, double y)
    {
        super(x, y);
    }

    public Tile(Point2D position, Item item) {
        this(position);
        this.item = item;
    }
    public Tile(double x, double y, Item item) {
        this(x, y);
        this.item = item;
    }

    public Item getItem()
    {
        return item;
    }
}
