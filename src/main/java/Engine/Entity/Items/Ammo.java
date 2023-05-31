package Engine.Entity.Items;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Ammo extends Item {

    /**
     * Default amount of ammunition
     */
    public static final int DEFAULT_AMOUNT = 30;
    private static final Image image = new Image(new File("./src/main/assets/ammo.png").toURI().toString(),
            ITEM_SIZE, ITEM_SIZE, false, false);

    /**
     * Creates an ammo on the specified coordinates
     *
     * @param x     x coordinate
     * @param y     y coordinate
     */
    public Ammo(double x, double y, int amount) {
        super(image, x, y, amount, Type.AMMO);
    }

    /**
     * Creates an ammo on the specified point
     *
     * @param position Point2D position
     * @param amount amount of ammunition
     */
    public Ammo(Point2D position, int amount) {
        this(position.getX(), position.getY(), amount);
    }
}
