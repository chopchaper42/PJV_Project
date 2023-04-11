package Engine.Level;

import Engine.Tile;

import java.util.List;

public record LevelInfo(List<Tile> tiles, int height, int width) {}