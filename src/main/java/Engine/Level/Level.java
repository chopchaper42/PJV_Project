package Engine.Level;

import Engine.Entity.Entity;
import Engine.Graphics;
import Engine.Tile;
import Engine.Tiles.Floor;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Level
{
    private final GraphicsContext graphics = Graphics.getGraphics();
    private static List<Tile> tiles = new ArrayList<>();

    public Level(File file) {
        reader = new LevelReader();
        tiles = reader.readLevel(file);
        canvas.setWidth(reader.levelWidth * Tile.TILE_SIZE);
        canvas.setHeight(reader.levelHeight * Tile.TILE_SIZE);
    }

    public void render() {
        tiles.forEach(this::renderTile);
    }

    public List<Tile> getTiles()
    {
        return tiles;
    }

    public static Point2D getFirstFloorTile() {
        for (Entity tile : tiles) {
            if (tile instanceof Floor)
                return tile.getPosition();
        }
        return null;
    }

    private void renderTile(Entity tile) {
        graphics.drawImage(tile.getImage(), tile.getX(), tile.getY());
    }
}
