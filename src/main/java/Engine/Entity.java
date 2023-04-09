package Engine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Entity
{
    private Image image;
    private double x;
    private double y;
    private int health = 100;
    private double speed;
    private Point2D direction;

    public Entity(Point2D position, int health) {
        this.x = position.getX();
        this.y = position.getY();
        this.health = health;
    }
    public Entity(Point2D position) {
        this(position, 100);
    }

    public int getHealth()
    {
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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
}
