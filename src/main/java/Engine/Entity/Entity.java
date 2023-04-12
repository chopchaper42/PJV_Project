package Engine.Entity;

import Engine.Graphics;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public abstract class Entity
{
    private Image image;
    private double x;
    private double y;
    private Rectangle2D boundaries;

    public Entity(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
        boundaries = new Rectangle2D(x, y, image.getWidth(), image.getHeight());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    public Image getImage()
    {
        return image;
    }

    void moveX(double x) {
        this.x += x;
    }

    void moveY(double y) {
        this.y += y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void draw()
    {
        Graphics.getGraphics().drawImage(image, getX(), getY());
    }

    public Rectangle2D getBoundaries()
    {
        return boundaries;
    }

    void setBoundaries(double x, double y, double width, double height) {
        boundaries = new Rectangle2D(x, y, width, height);
    }
}
