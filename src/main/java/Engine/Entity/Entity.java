package Engine.Entity;

import Engine.SquareBoundaries;
import Logs.Logger;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
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
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate
     * @return y coordiante
     */
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the entity's position
     * @return position
     */
    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    /**
     * Draws the entity on canvas
     */
    public void draw(Canvas canvas)
    {
        canvas.getGraphicsContext2D().drawImage(image, x, y);
    }

    /**
     * Returns the entity's boundaries
     * @return entity's boundaries
     */
    public Rectangle2D getBoundaries()
    {
        return boundaries;
    }

    public void setBoundaries(double x, double y, double width, double height) {
        this.boundaries = new Rectangle2D(x, y, width, height);
    }
    public void setBoundaries(double x, double y, double size) {
        this.boundaries = new SquareBoundaries(x, y, size);
    }
    public void setBoundaries(Rectangle2D bounds) {
        this.boundaries = bounds;
    }

    /**
     * Returns the central point of an entity
     * @return the central point
     */
    public Point2D center() {
        double x = getX() + (image.getWidth() / 2);
        double y = getY() + (image.getHeight() / 2);
        return new Point2D(x, y);
    }

    protected void setImage(Image image) {
        this.image = image;
    }
    public Image getImage() {
        return this.image;
    }
    public void logCoordinates() {
        Logger.log("\n" + toString() + "\n\tX: " + getX() + "\n\tY:" + getY() + "\n\tbounds X: "
                + boundaries.getMinX() + "\n\tbounds Y:" + boundaries.getMinY());
    }

    public double width() {
        return boundaries.getWidth();
    }
    public double height() {
        return boundaries.getHeight();
    }
}
