package Engine.Entity;

import Engine.Entity.Tiles.Wall;
import Engine.Game;
import Engine.Item;
import Engine.Level.Level;
import Utility.Pythagoras;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;

public class Player extends LivingEntity
{
    private final int SPEED_PER_SECOND = 1000;
    private List<Item> inventory;
    private final static Image image = new Image(new File("./src/main/assets/player.png").toURI().toString());

    public Player(double x, double y)
    {
        super(image, x, y);
        setBoundaries(getX(), getY(), image.getWidth(), image.getHeight());

    }
    public Player(Point2D point) {
        this(point.getX(), point.getY());
    }

    private void moveDiagonal(double distance, int scaleX, int scaleY) {
        moveX(distance * scaleX);
        moveY(distance * scaleY);
    }

    public void handleInput(boolean W_pressed, boolean A_pressed, boolean S_pressed, boolean D_pressed, double dt) {
        double dx = 0;
        double dy = 0;
        double distance = getSpeedPerSecond() * dt;
        Rectangle2D newBoundaries;

        if (W_pressed && !(D_pressed || A_pressed)) {
            dy = -distance;
            //moveY(-distance);
        }

        if (W_pressed && D_pressed) {
            dx = Pythagoras.leg(distance);
            dy = -1 * Pythagoras.leg(distance);
//                moveDiagonal(Pythagoras.leg(distance), 1, -1);
        }

        if (W_pressed && A_pressed) {
            dx = -1 * Pythagoras.leg(distance);
            dy = -1 * Pythagoras.leg(distance);
//                moveDiagonal(Pythagoras.leg(distance), -1, -1);
        }

        if (A_pressed && !(W_pressed || S_pressed)) {
            dx = -1 * distance;
//                moveX(-distance);
        }

        if (S_pressed && !(D_pressed || A_pressed)) {
            dy = distance;
//                moveY(distance);
        }

        if (S_pressed && A_pressed) {
            dx = -1 * Pythagoras.leg(distance);
            dy = Pythagoras.leg(distance);
//                moveDiagonal(Pythagoras.leg(distance), -1, 1);
        }

        if (S_pressed && D_pressed) {
            dx = Pythagoras.leg(distance);
            dy = Pythagoras.leg(distance);
//                moveDiagonal(Pythagoras.leg(distance), 1, 1);
        }

        if (D_pressed && !(S_pressed || W_pressed)) {
            dx = distance;
//                moveX(distance);
        }


        newBoundaries = new Rectangle2D(getX() + dx, getY() + dy, image.getWidth(), image.getHeight());
        if (!checkCollision(Game.getLevel().getTiles(), newBoundaries)) {
            moveX(dx);
            moveY(dy);
        }

    }

    public int getSpeedPerSecond()
    {
        return SPEED_PER_SECOND;
    }

    public List<Item> getInventory()
    {
        return inventory;
    }
    public <T> boolean checkCollision(List<T> entities, Rectangle2D newBoundaries) {
        boolean intersects = false;
        boolean isWall = false;
        boolean collides = false;

        for (T entity : entities) {
            if (!collides) {
//                intersects = ((Entity) entity).getBoundaries().intersects(getBoundaries());
                intersects = ((Entity) entity).getBoundaries().intersects(newBoundaries);
                isWall = entity instanceof Wall;
                collides = intersects && isWall;
            }

        }
        return collides;
    }
}
