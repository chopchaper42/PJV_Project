package Engine.Entity.Items;

import Engine.Entity.Entity;
import Engine.Entity.Tile.Tile;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Item extends Entity
{
    protected static final int ITEM_SIZE = 48;
    private int amount;
    private final Type type;

    protected Item(Image image, double x, double y, int amount, Type type) {
        super(image, x, y);
        this.amount = amount;
        this.type = type;
    }

    protected Item(Type type, int amount) {
        super(null, 0, 0);
        this.type = type;
        this.amount = amount;
    }

    /**
     * @return item's amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return item's type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param x tile's x coordinate
     * @param y tile's y coordinate
     * @return point, in which the item will be centered
     */
    public static Point2D getCoordinatesForCenter(double x, double y) {
        return new Point2D(
                x + (Tile.TILE_SIZE - ITEM_SIZE) / 2f,
                y + (Tile.TILE_SIZE - ITEM_SIZE) / 2f
                );
    }
}
