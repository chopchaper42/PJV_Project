package Engine.Level;

import Engine.Entity.Enemy;
import Engine.Entity.Items.Ammo;
import Engine.Entity.Items.Heal;
import Engine.Entity.Items.Item;
import Engine.Entity.Items.Key;
import Engine.Entity.Tile.Door;
import Engine.Entity.Tile.Floor;
import Engine.Entity.Tile.Tile;
import Engine.Entity.Tile.Wall;
import Logs.Logger;
import javafx.geometry.Point2D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelReader
{
    /**
     * Reads a level from the specified file
     * @param file file with level description
     * @return {@code levelInfo} if the reading went good, otherwise {@code null}
     */
    public static LevelInfo readLevel(File file)
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
    private static LevelInfo parseLevel(BufferedReader reader) throws IOException
    {
        String currentLine;
        List<Tile> tiles = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();
        Point2D playerPosition = null;
        Point2D friendPosition = null;
        int levelWidth = 0;
        int levelHeight = 0;
        int rowNumber = 0;

        while ( (currentLine = reader.readLine()) != null)
        {
            String[] levelRow = currentLine.split(" ");
            int posY = rowNumber * Tile.TILE_SIZE;
            int posX = 0;

            levelWidth = 0;
            levelHeight += Tile.TILE_SIZE;

            for (String s : levelRow) {

                switch (s) {
                    case "W" -> tiles.add(new Wall(posX, posY));
                    case "D" -> tiles.add(new Door(posX, posY));
                    case "A" -> {
                        tiles.add(new Floor(posX, posY));
                        items.add(new Ammo(Item.getCoordinatesForCenter(posX, posY), Ammo.DEFAULT_AMOUNT));
                    }
                    case "H" -> {
                        tiles.add(new Floor(posX, posY));
                        items.add(new Heal(Item.getCoordinatesForCenter(posX, posY), Heal.DEFAULT_AMOUNT));
                    }
                    case "K" -> {
                        tiles.add(new Floor(posX, posY));
                        items.add(new Key(Item.getCoordinatesForCenter(posX, posY), Key.DEFAULT_AMOUNT));
                    }
                    case "E" -> {
                        tiles.add(new Floor(posX, posY));
                        enemies.add(new Enemy(posX, posY));
                    }
                    case "F" -> {
                        tiles.add(new Floor(posX, posY));
                        playerPosition = new Point2D(posX, posY);
                    }
                    case "P" -> {
                        tiles.add(new Floor(posX, posY));
                        friendPosition = new Point2D(posX, posY);
                    }
                    default -> {
                        tiles.add(new Floor(posX, posY));
                        if (playerPosition == null)
                            playerPosition = new Point2D(posX, posY);
                    }
                }

                posX += Tile.TILE_SIZE;
                levelWidth += Tile.TILE_SIZE;
            }
            rowNumber++;
        }
        return new LevelInfo(tiles, items, enemies, playerPosition, friendPosition, levelHeight, levelWidth);
    }
}
