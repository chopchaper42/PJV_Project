package Engine.Entity;

import Engine.Speed;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Bullet extends MovingEntity
{
    private static Image image = new Image(new File("./src/main/assets/bullet.png").toURI().toString());
    private Speed speed;
    private Entity source;
    private final int DAMAGE = 25;

    /**
     * Creates a bullet
     * @param source from where the bullet flies
     * @param target to where the bullet flies
     */
    public Bullet(Entity source, Point2D target, double speed)
    {
        super(image, source.center().getX(), source.center().getY());
        this.source = source;
        this.speed = new Speed(source.getPosition(), target, speed);
    }

    /**
     * Creates a bullet
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param dx speed along the x-axis
     * @param dy speed along the y-axis
     */
    public Bullet(double x, double y, double dx, double dy)
    {
        super(image, x, y);
        this.speed = new Speed(dx, dy);
    }


    /**
     * Moves a bullet
     * @param dt time elapsed since the last frame
     */
    public void move(double dt) {
        move(speed.xComponent() * dt, speed.yComponent() * dt);
    }

    public int damage() {
        return DAMAGE;
    }
    public Entity source() {
        return source;
    }

    public double getSpeedX() {
        return speed.xComponent();
    }
    public double getSpeedY() {
        return speed.yComponent();
    }
}