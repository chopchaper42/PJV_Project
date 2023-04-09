package Engine;

import Engine.Tiles.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.util.List;

public class Level
{
    LevelReader reader;
    static List<Tile> tiles;
    GraphicsContext graphics;

    public Level(File file, Canvas canvas) {
        reader = new LevelReader();
        tiles = reader.readLevel(file);
        this.graphics = canvas.getGraphicsContext2D();
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

    private void renderTile(Tile tile) {
        graphics.drawImage(tile.getImage(), tile.getX(), tile.getY());
    }
}
