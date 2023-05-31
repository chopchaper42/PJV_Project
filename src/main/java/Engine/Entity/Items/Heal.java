package Engine.Entity.Items;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Heal extends Item {

    /**
     * Default heal amount
     */
    public static final int DEFAULT_AMOUNT = 20;
    private static final Image image = new Image(new File("./src/main/assets/heal.png").toURI().toString(),
            ITEM_SIZE, ITEM_SIZE, false, false);

    /**
     * Creates a heal item on the specified coordinates
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param amount heal amount
     */
    public Heal(double x, double y, int amount) {
        super(image, x, y, amount, Type.HEAL);
    }

    /**
     * Creates a heal item on the specified point
     *
     * @param position Point2D position
     * @param amount heal amount
     */
    public Heal(Point2D position, int amount) {
        this(position.getX(), position.getY(), amount);
    }
}
