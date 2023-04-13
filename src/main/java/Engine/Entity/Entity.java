package Engine.Entity;

import Engine.Graphics;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Represents an entity
 */
public abstract class Entity
{
    private Image image;
    private double x;
    private double y;
    private Rectangle2D boundaries;

    /**
     * Creates an entity
     * @param image entity's image
     * @param x x coordinate
     * @param y y coordinate
     */
    public Entity(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
        boundaries = new Rectangle2D(x, y, image.getWidth(), image.getHeight());
    }

    /**
     * Returns the x coordinate
     * @return x coordiante
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the y coordinate
     * @return y coordiante
     */
    public double getY() {
        return y;
    }
    /**
     * Returns the entity's position
     * @return position
     */
    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    void moveX(double x) {
        this.x += x;
    }

    void moveY(double y) {
        this.y += y;
    }

    /**
     * Draws the entity on canvas
     */
    public void draw()
    {
        Graphics.getGraphics().drawImage(image, getX(), getY());
    }

    /**
     * Returns the entity's boundaries
     * @return entity's boundaries
     */
    public Rectangle2D getBoundaries()
    {
        return boundaries;
    }

    void setBoundaries(double x, double y, double width, double height) {
        this.boundaries = new Rectangle2D(x, y, width, height);
    }

    /**
     * Returns the central point of an entity
     * @return the central point
     */
    public Point2D getCenter() {
        double x = getX() + (image.getWidth() / 2);
        double y = getY() + (image.getHeight() / 2);
        return new Point2D(x, y);
    }
}
