package Engine.Entity;

import Engine.ActionField;
import Engine.Entity.Items.*;
import Engine.Entity.Tile.Door;
import Engine.Entity.Tile.Tile;
import Engine.Inventory;
import Logs.Logger;
import Utility.Window;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import network.udp.client.ClientControllerSingleton;

import java.io.File;
import java.util.List;

/**
 * Represents a player
 */
public class Player extends LivingEntity
{
    private final int SPEED_PER_SECOND = 1500;
    public static final double SIZE = 30;
    private final Inventory inventory;
    private final double screenPositionX;
    private final double screenPositionY;
    private final Canvas canvas;
    private final ActionField actionField;
    private boolean alive = true;
    private final static Image alivePlayer = new Image(
            new File("./src/main/assets/player1.png").toURI().toString(),
            SIZE, SIZE,
            false,
            false);
    private final static Image deadPlayer = new Image(
            new File("./src/main/assets/dead_player1.png").toURI().toString(),
            SIZE, SIZE,
            false,
            false);

    /**
     * Creates a player.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Player(Window window, double x, double y, Inventory inventory) {
        super(alivePlayer, x, y, 100);
        canvas = new Canvas(window.width(), window.height());
        setBoundaries(getX(), getY(), alivePlayer.getWidth(), alivePlayer.getHeight());
        screenPositionX = (window.width() - SIZE) / 2;
        screenPositionY = (window.height() - SIZE) / 2;
        this.inventory = inventory;
        this.actionField = new ActionField(0.5, this);
    }

    // Empty constructor for testing
    public Player(Image image)
    {
        super(image, 0, 0, 100);
        canvas = null;
        screenPositionX = 0;
        screenPositionY = 0;
        this.inventory = null;
        this.actionField = null;
    }

    /**
     * @return the player's speed per second
     */
    public int getSpeedPerSecond()
    {
        return SPEED_PER_SECOND;
    }

    /**
     * @return the player's inventory.txt
     */
    public Inventory getInventory()
    {
        return inventory;
    }

    public int getItemAmount(Type type) {
        return inventory.getAmount(type);
    }
    public void decreaseItem(Type type) {
        inventory.use(type);
    }
    private void increaseItem(Type type, int x) {
        inventory.add(type, x);
    }

    /**
     * Takes items, if the player intersects with them
     *
     * @param items items
     */
    public void takeItems(List<Item> items) {
        items.forEach(item -> {
            if (item.getType() != Type.HEAL) {
                increaseItem(item.getType(), item.getAmount());
                Logger.log("+" + item.getAmount() + " " + item.getType().name() + ". Total: " + inventory.getAmount(item.getType()));
            } else {
                increaseHealth(item.getAmount());
                Logger.log("+" + item.getAmount() + " " + item.getType().name() + ". Health: " + getHealth());
            }

            ClientControllerSingleton.getInstance().send("item",
                    item.getX(),
                    item.getY()
            );
        });
    }

    /**
     * Tries to open a door
     *
     * @param tiles tiles
     */
    public void tryOpenDoor(List<Tile> tiles) {
        actionField.updateToMatchCoordinates();

        if (inventory.getAmount(Type.KEY) == 0) {
            Logger.log("No keys in inventory.txt");
            return;

        }
        for (Tile tile : tiles) {
            if (tile instanceof Door door) {
                if (actionField.bounds().intersects(door.getBoundaries())) {
                    inventory.use(Type.KEY);
                    door.open();
                    ClientControllerSingleton.getInstance().send("door", door.getX(), door.getY());
                }
            }
        }
    }

    /**
     * Shoots in the direction of mouse click
     *
     * @param event mouse click event
     * @param bullets bullets
     */
    public void shoot(MouseEvent event, List<Bullet> bullets) {
        if (getItemAmount(Type.AMMO) > 0) {
            Point2D direction = new Point2D(
                    getX() - (positionOnCanvasX() - event.getX()),
                    getY() - (positionOnCanvasY() - event.getY())
            );
            Bullet bullet = new Bullet(this, direction, 6500);
            bullets.add(bullet);
            ClientControllerSingleton.getInstance().send("bullet", bullet.getX(), bullet.getY(),
                    bullet.getSpeedX(), bullet.getSpeedY());
            decreaseItem(Type.AMMO);
        }
    }

    /**
     * Draws the player
     * @param image image
     */
    public void draw(Image image) {
        canvas.getGraphicsContext2D().drawImage(image, screenPositionX, screenPositionY);
    }

    public double positionOnCanvasX() {
        return screenPositionX;
    }

    public double positionOnCanvasY() {
        return screenPositionY;
    }
    public Canvas canvas() {
        return canvas;
    }

    public boolean alive() {
        return alive;
    }
    public ActionField actionField() {
        return actionField;
    }

    /**
     * Kills the player
     */
    public void kill() {
        alive = false;
        setImage(deadPlayer);
        draw(getImage());
        decreaseHealth(1000000);
    }
}
