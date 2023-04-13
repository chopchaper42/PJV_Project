package Engine.Level;

import Engine.Entity.Tiles.Tile;

import java.util.List;

/**
 * Represents an information about a level
 * @param tiles
 * @param height
 * @param width
 */
public record LevelInfo(List<Tile> tiles, int height, int width) {}