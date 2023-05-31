package Engine.Entity.Items;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Key extends Item {

    public static final int DEFAULT_AMOUNT = 1;
    private final static Image image = new Image(new File("./src/main/assets/key.png").toURI().toString(),
            ITEM_SIZE, ITEM_SIZE, false, false);

    /**
     * Creates a key
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param amount item's amount
     */
    public Key(double x, double y, int amount) {
        super(image, x, y, amount, Type.KEY);
    }

    /**
     * Creates a key
     *
     * @param position Point2D position
     * @param amount amount
     */
    public Key(Point2D position, int amount) {
        this(position.getX(), position.getY(), amount);
    }
}
