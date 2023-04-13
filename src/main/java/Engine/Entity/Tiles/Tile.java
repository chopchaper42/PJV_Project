package Engine.Entity.Tiles;

import Engine.Entity.Entity;
import Engine.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Tile extends Entity
{
    /**
     * The size of a tile
     */
    public static final int TILE_SIZE = 64;
    private Item item;

    /**
     * Creates a tile
     * @param image tile's image
     * @param x x coordinate
     * @param y y coordinate
     */
    public Tile(Image image, double x, double y)
    {
        super(image, x, y);
    }

    /**
     * Creates a tile with an item
     * @param image tile's image
     * @param x x coordinate
     * @param y y coordinate
     * @param item item
     */
    public Tile(Image image, double x, double y, Item item) {
        this(image, x, y);
        this.item = item;
    }

    /**
     * @return the item that tile has
     */
    public Item getItem()
    {
        return item;
    }
}
