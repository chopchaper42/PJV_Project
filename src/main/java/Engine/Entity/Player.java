package Engine.Entity;

import Engine.Game;
import Engine.Item;
import Utility.Collisions;
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
    private final static Image image = new Image(new File("./src/main/assets/player.png").toURI().toString(), 30, 30, false, false);

    public Player(double x, double y)
    {
        super(image, x, y);
        setBoundaries(getX(), getY(), image.getWidth(), image.getHeight());

    }
    public Player(Point2D point) {
        this(point.getX(), point.getY());
    }

    public void handleInput(boolean W_pressed, boolean A_pressed, boolean S_pressed, boolean D_pressed, double dt) {
        double dx = 0;
        double dy = 0;
        double distance = getSpeedPerSecond() * dt;

        if (W_pressed && !(D_pressed || A_pressed)) {
            dy = -distance;
            //moveY(-distance);
        }

        if (W_pressed && D_pressed) {
            dx = Pythagoras.leg45deg(distance);
            dy = -1 * Pythagoras.leg45deg(distance);
//                moveDiagonal(Pythagoras.leg(distance), 1, -1);
        }

        if (W_pressed && A_pressed) {
            dx = -1 * Pythagoras.leg45deg(distance);
            dy = -1 * Pythagoras.leg45deg(distance);
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
            dx = -1 * Pythagoras.leg45deg(distance);
            dy = Pythagoras.leg45deg(distance);
//                moveDiagonal(Pythagoras.leg(distance), -1, 1);
        }

        if (S_pressed && D_pressed) {
            dx = Pythagoras.leg45deg(distance);
            dy = Pythagoras.leg45deg(distance);
//                moveDiagonal(Pythagoras.leg(distance), 1, 1);
        }

        if (D_pressed && !(S_pressed || W_pressed)) {
            dx = distance;
//                moveX(distance);
        }


        Rectangle2D newBoundariesX = new Rectangle2D(getX() + dx, getY(), image.getWidth(), image.getHeight());
        Rectangle2D newBoundariesY = new Rectangle2D(getX(), getY() + dy, image.getWidth(), image.getHeight());
        if (!Collisions.checkCollision(Game.getLevel().getTiles(), newBoundariesX)) {
            moveX(dx);

        }
        if (!Collisions.checkCollision(Game.getLevel().getTiles(), newBoundariesY)) {
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
}
