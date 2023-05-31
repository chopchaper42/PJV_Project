package Engine.Entity;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Friend extends LivingEntity{
    private static Image image = new Image(
            new File("./src/main/assets/player2.png").toURI().toString(),
            30, 30,
            false,
            false);

    /**
     * Creates a living entity
     *
     * @param x      x coordinate
     * @param y      y coordinate
     * @param health health
     */
    public Friend(double x, double y, int health) {
        super(image, x, y, health);
    }
    /**
     * Creates a living entity
     *
     * @param position Point2D position
     * @param health health
     */
    public Friend(Point2D position, int health) {
        super(image, position.getX(), position.getY(), health);
    }


    /**
     * Draws a friend
     * @param canvas canvas
     */
    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(image, getX(), getY());
    }

    public void kill()
    {
        setImage(
                new Image(new File("./src/main/assets/player2.png").toURI().toString(),
                30, 30,
                false,
                false)
        );
    }
}
