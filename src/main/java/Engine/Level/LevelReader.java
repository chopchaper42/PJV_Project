package Engine.Level;

import Engine.Entity.Entity;
import Engine.Tile;
import Engine.Tiles.Floor;
import Engine.Tiles.Wall;
import Logs.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelReader
{
    public LevelInfo readLevel(File file)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return parseLevel(reader);

        } catch (IOException exception) {
            Logger.log(exception.getMessage());
            System.exit(-1);

        }
        return null;
    }

    /*
     * Level editor will always generate a "rectangular" text file,
     * so on each row the width of the level is constant.
     *
     * What if the Level editor will make a list of Tiles from the user's input and serialize it to JSON,
     * so here we will only deserialize it
     */
    private LevelInfo parseLevel(BufferedReader reader) throws IOException
    {
        int levelWidth = 0;
        int levelHeight = 0;
        String currentLine;
        int rowNumber = 0;

        while ( (currentLine = reader.readLine()) != null)
        {
            String[] levelRow = currentLine.split(" ");
            int posY = rowNumber * Tile.TILE_SIZE;
            int posX = 0;

            levelHeight += Tile.TILE_SIZE;

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
            rowNumber++;
        }
    }
}
