package Engine.Entity.Tiles;

import Engine.Entity.Entity;
import Engine.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Tile extends Entity
{
    public static final int TILE_SIZE = 64;
    private Item item;

    public Tile(Image image, double x, double y)
    {
        super(image, x, y);
    }
    public Tile(Image image, double x, double y, Item item) {
        this(image, x, y);
        this.item = item;
    }

    public Item getItem()
    {
        return item;
    }
}
