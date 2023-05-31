package Engine.Level;

import Engine.Entity.Enemy;
import Engine.Entity.Items.Item;
import Engine.Entity.Tile.Tile;
import javafx.geometry.Point2D;

import java.util.List;

/**
 * Represents an information about a level
 * @param tiles
 * @param height
 * @param width
 */
public record LevelInfo(List<Tile> tiles, List<Item> items, List<Enemy> enemies, Point2D playerPosition, Point2D friendPosition, int height, int width) {}