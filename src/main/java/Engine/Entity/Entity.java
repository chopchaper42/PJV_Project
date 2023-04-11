package Engine.Entity;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity
{
    private Image image;
    private double x;
    private double y;

    public Entity(Point2D position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
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

    public void draw(GraphicsContext graphics)
    {
        graphics.drawImage(image, getX(), getY());
    }
}
