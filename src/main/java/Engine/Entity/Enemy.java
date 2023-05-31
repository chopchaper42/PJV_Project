package Engine.Entity;

import Engine.ActionField;
import Engine.Entity.Tile.Tile;
import Engine.Speed;
import Engine.SquareBoundaries;
import Logs.Logger;
import Utility.Collisions;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import network.udp.client.ClientControllerSingleton;

import java.io.File;
import java.util.List;
import java.util.Random;

public class Enemy extends LivingEntity {

    private final static int ENEMY_SIZE = 30;
    private final static int SPEED = 900;
    private final ActionField visionField;
    private final ActionField movementField;
    private double shootingRate;
    private double movementRate;
    private double defaultMovementRate;
    private boolean newDestinationCalculated = false;
    private int direction = 1;
    private double elapsedTime = 0;
    private boolean seeingPlayer;
    private Point2D destination;
    private Speed speed;
    private double timeSinceLastShot = 0;
    private final static Image image = new Image(
            new File("./src/main/assets/enemy.png").toURI().toString(),
            ENEMY_SIZE, ENEMY_SIZE,
            false,
            false);

    /**
     * Creates an enemy
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Enemy(double x, double y) {
        super(image, x, y, 50);
        this.visionField = new ActionField(5, this);
        this.movementField = new ActionField(3, this);
        this.seeingPlayer = false;
        defaultMovementRate = new Random().nextDouble(0.3, 1);
        shootingRate = new Random().nextDouble(0.1, 0.6);
    }

    /**
     * Shoot in player
     *
     * @param player player
     * @param bullets bullets
     * @param dt time elapsed since the last frame
     */
    public void shoot(Player player, List<Bullet> bullets, double dt) {
        if (seeingPlayer) {
            addTimeToLastShot(dt);
            if (timeSinceLastShot > shootingRate) {
                Point2D direction = new Point2D(player.center().getX(), player.center().getY());
                Bullet bullet = new Bullet(this, direction, 2500);
                bullets.add(bullet);
                ClientControllerSingleton.getInstance().send("bullet", bullet.getX(), bullet.getY(),
                        bullet.getSpeedX(), bullet.getSpeedY());
                Logger.log(this + "'s shot");
                timeSinceLastShot = 0;
            }
        }
    }

    /**
     * Calculates a point where to go
     */
    public void calculateDestination() {
        destination = pickWhereToGo();
        speed = new Speed(this.getPosition(), destination, SPEED);
        newDestinationCalculated = true;
    }

    /**
     * moves enemy
     *
     * @param tiles tiles
     * @param dt elapsed time since the last frame
     */
    public void move(List<Tile> tiles, double dt) {
        setRushMode();
        if (elapsedTime > movementRate) {
            if (!newDestinationCalculated) {
                calculateDestination();
            }

            double newX = getX() + speed.xComponent() * dt; // same logic is in player. Try to unite.
            double newY = getY() + speed.yComponent() * dt;
            Rectangle2D newBounds = new SquareBoundaries(newX, newY, ENEMY_SIZE);

            boolean colliding = Collisions.checkWallCollision(newBounds, tiles);

            if (colliding || reachedDestination(destination)){
                Logger.log("Collision or reached destination");
                newDestinationCalculated = false;
                elapsedTime = 0;
                return;
            }

            move(speed.xComponent() * dt, speed.yComponent() * dt);
            ClientControllerSingleton.getInstance().send("enemy", getX(), getY());
            updateFields();
        } else {
            elapsedTime += dt;

        }
    }

    private boolean reachedDestination(Point2D destination) {
        return (destination.getX() - getX()) * direction < 0;
    }

    public ActionField visionField() {
        return visionField;
    }

    public boolean seeingPlayer() {
        return seeingPlayer;
    }

    public void setSeeingPlayer(boolean seeing) {
        seeingPlayer = seeing;
    }

    private void addTimeToLastShot(double dt) {
        timeSinceLastShot += dt;
    }

    private Point2D pickWhereToGo() {
        Random rnd = new Random();
        double x = rnd.nextDouble(movementField.bounds().getMinX(), movementField.bounds().getMaxX());
        double y = rnd.nextDouble(movementField.bounds().getMinY(), movementField.bounds().getMaxY());
        direction = x - getX() > 0 ? 1 : -1;
        return new Point2D(x, y);
    }

    private void updateFields() {
        visionField.updateToMatchCoordinates();
        movementField.updateToMatchCoordinates();
    }

    private void setRushMode() {
        if (seeingPlayer)
            movementRate = 0;
        else
            movementRate = defaultMovementRate;
    }
}
