package Engine.Entity.Tile;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;

public class Tile extends Entity
{
    /**
     * The size of a tile
     */
    public static final int TILE_SIZE = 64;
    boolean solid = false;

    Tile(Image image, double x, double y, boolean solid) {
        super(image, x, y);
        this.solid = solid;
    }

    public boolean solid() {
        return solid;
    }



}
