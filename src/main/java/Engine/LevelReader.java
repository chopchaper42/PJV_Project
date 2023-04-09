package Engine;

import Engine.Tiles.Floor;
import Engine.Tiles.Tile;
import Engine.Tiles.Wall;
import Logs.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelReader
{
    BufferedReader reader;
    int levelWidth;
    int levelHeight;
    public List<Tile> readLevel(File file)
    {
        List<Tile> tiles = new ArrayList<Tile>();
        try {
            reader = new BufferedReader(new FileReader(file));
            parseLevel(tiles);

        } catch (IOException exception) {
            Logger.log(exception.getMessage());
            System.exit(-1);
        }
        return tiles;
    }

    private void setLevelWidth(int width) {
        if (width > levelWidth)
            levelWidth = width;
    }

    private void parseLevel(List<Tile> tiles) throws IOException
    {
        String line;
        int row = 0;

        while ((line = reader.readLine()) != null)
        {
            int posY = row * Tile.TILE_SIZE;
            int posX = 0;
            String[] levelRow = line.split(" ");
            setLevelWidth(levelRow.length); //sets level width in blocks to define canvas width
            levelHeight++;
            for (String s : levelRow) {

                switch (s) {
                    case "2":
                        tiles.add(new Floor(posX, posY));
                        break;
                    default:
                        tiles.add(new Wall(posX, posY));
                        break;
                }
                posX += Tile.TILE_SIZE;
            }
            row++;
        }
    }
}
