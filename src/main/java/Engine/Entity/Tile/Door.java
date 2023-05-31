package Engine.Entity.Tile;

import javafx.scene.image.Image;

import java.io.File;

public class Door extends Tile {
    private static final Image CLOSED_DOOR_IMG = new Image(new File("./src/main/assets/door.png").toURI().toString());
    private static final Image OPENED_DOOR_IMG = new Image(new File("./src/main/assets/floor.png").toURI().toString());


    /**
     * @param x x coordinate
     * @param y y coordinate
     */
    public Door(double x, double y) {
        super(CLOSED_DOOR_IMG, x, y, true);
    }

    /**
     * Opens a door
     */
    public void open() {
        solid = false;
        setImage(OPENED_DOOR_IMG);
    }
}
