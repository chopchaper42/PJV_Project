package Engine;

import Engine.Entity.Bullet;
import Engine.Entity.Items.Type;
import Engine.Entity.Player;
import Engine.Level.Level;
import Utility.Collisions;
import Utility.Pythagoras;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import network.udp.client.ClientControllerSingleton;

import java.util.List;

public class InputManager {
    Player player;
    Level level;
    boolean UP = false;
    boolean LEFT = false;
    boolean DOWN = false;
    boolean RIGHT = false;

    /**
     * Creates an input manager
     *
     * @param player player
     * @param level level
     */
    public InputManager(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    /**
     * Handles user input
     *
     * @param dt time elapsed since the last frame
     */
    public void handleInput(double dt) {
        double dx = 0;
        double dy = 0;
        double distance = player.getSpeedPerSecond() * dt;

        if (UP && !(RIGHT || LEFT)) {
            dy = -distance;
        }

        if (UP && RIGHT) {
            dx = Pythagoras.leg45deg(distance);
            dy = -1 * Pythagoras.leg45deg(distance);
        }

        if (UP && LEFT) {
            dx = -1 * Pythagoras.leg45deg(distance);
            dy = -1 * Pythagoras.leg45deg(distance);
        }

        if (LEFT && !(UP || DOWN)) {
            dx = -1 * distance;
        }

        if (DOWN && !(RIGHT || LEFT)) {
            dy = distance;
        }

        if (DOWN && LEFT) {
            dx = -1 * Pythagoras.leg45deg(distance);
            dy = Pythagoras.leg45deg(distance);
        }

        if (DOWN && RIGHT) {
            dx = Pythagoras.leg45deg(distance);
            dy = Pythagoras.leg45deg(distance);
        }

        if (RIGHT && !(DOWN || UP)) {
            dx = distance;
        }

        Rectangle2D newBoundariesX = new SquareBoundaries(player.getX()+ dx, player.getY(), Player.SIZE);
        Rectangle2D newBoundariesY =  new SquareBoundaries(player.getX(), player.getY() + dy, Player.SIZE);

        if (dx != 0 && !Collisions.checkWallCollision(newBoundariesX, level.tiles())) {
            player.move(dx, 0);
            level.moveCanvas(dx, 0);
        }
        if (dy != 0 && !Collisions.checkWallCollision(newBoundariesY, level.tiles())) {
            player.move(0, dy);
            level.moveCanvas(0, dy);
        }

        player.setBoundaries(player.getX(), player.getY(), Player.SIZE);
        ClientControllerSingleton.getInstance().send("playerPosition", player.getX(), player.getY());
    }

    public void release(KeyEvent event)
    {
        handle(event.getCode(), false);
    }

    public void press(KeyEvent event) {
        handle(event.getCode(), true);
    }

    private void handle(KeyCode code, boolean pressed) {
        switch (code) {
            case W -> UP = pressed;
            case A -> LEFT = pressed;
            case S -> DOWN = pressed;
            case D -> RIGHT = pressed;
            case SPACE -> {
                if (pressed)
                    player.tryOpenDoor(level.tiles());
            }
            case K -> player.kill();
            case Q -> {
                if (pressed)
                    GameSettings.toggleShowFields();
            }
        }
    }
}
