package Engine.Level;

import Engine.Entity.Entity;
import Engine.Graphics;
import Engine.Entity.Tiles.Tile;
import Engine.Entity.Tiles.Floor;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.util.List;

public final class Level
{
    private final GraphicsContext graphics = Graphics.getGraphics();
    private LevelInfo level = null;

    public Level(File file) {
        level = LevelReader.readLevel(file);
        Canvas canvas = graphics.getCanvas();
        canvas.setWidth(level.width());
        canvas.setHeight(level.height());
    }

    public List<Tile> getTiles()
    {
        return level.tiles();
    }

    public Point2D getFirstFloorTile() {
        for (Entity tile : level.tiles()) {
            if (tile instanceof Floor)
                return tile.getPosition();
        }
        return null;
    }
}
