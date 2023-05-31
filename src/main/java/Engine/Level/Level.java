package Engine.Level;

import Engine.Entity.Bullet;
import Engine.Entity.Enemy;
import Engine.Entity.Friend;
import Engine.Entity.Items.Item;
import Engine.Entity.Player;
import Engine.Entity.Tile.Tile;
import Utility.Window;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Level
{
    private LevelInfo map;
    private Point2D initialCanvasPosition;
    private Point2D initialPlayerPosition;
    private Point2D initialFriendPosition;
    private final List<Item> items;
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies;
    private List<Friend> friends = new ArrayList<>();
    private Canvas canvas;
    private Window window;
    private boolean completed = false;

    /**
     * Creates a new level
     * @param file
     */
    public Level(Window window, File file) {
        map = LevelReader.readLevel(file);
        this.window = window;
        this.items = map.items();
        this.enemies = map.enemies();
        this.friends.add(new Friend(map.friendPosition(), 100));
        this.initialPlayerPosition = map.playerPosition();
        this.initialCanvasPosition = canvasPosition();
        this.canvas = createCanvas(map.width(), map.height());
    }

    /**
     * Moves the level's canvas
     *
     * @param dx a value to move on along the x-axis
     * @param dy a value to move on along the y-axis
     */
    public void moveCanvas(double dx, double dy) {
        initialCanvasPosition = new Point2D(
                initialCanvasPosition.getX() - dx,
                initialCanvasPosition.getY() - dy
        );
        canvas.setTranslateX(initialCanvasPosition.getX());
        canvas.setTranslateY(initialCanvasPosition.getY());

    }

    private Canvas createCanvas(double width, double height) {
        Point2D position = canvasPosition();
        Canvas canvas = new Canvas();
        canvas.setTranslateX(position.getX());
        canvas.setTranslateY(position.getY());
        canvas.setWidth(width);
        canvas.setHeight(height);
        return canvas;
    }

    public Point2D canvasPosition() {
        Point2D playerPosition = initialPlayerPosition();
        Point2D canvasPosition = new Point2D(
                window.getCenter(Player.SIZE).getX() - playerPosition.getX(),
                window.getCenter(Player.SIZE).getY() - playerPosition.getY()
        );
        return canvasPosition;
    }
    /**
     * @return the {@code Point2D} position of the first top-left floor tile, or {@code null} if there is no tiles
     */
    public Point2D initialPlayerPosition() { // DON'T LIKE IT!
        return initialPlayerPosition;
    }

    /**
     * Returns the tiles that level contains
     * @return tiles that level contains
     */
    public List<Tile> tiles()
    {
        return map.tiles();
    }

    public Canvas canvas() {
        return canvas;
    }

    public List<Bullet> bullets() {
        return bullets;
    }

    public List<Item> items() {
        return items;
    }

    public List<Enemy> enemies() {
        return enemies;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public boolean completed() {
        return enemies.isEmpty();
    }
}
