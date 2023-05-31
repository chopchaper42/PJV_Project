package Utility;

import Engine.Entity.Bullet;
import Engine.Entity.Enemy;
import Engine.Entity.Entity;
import Engine.Entity.Items.Item;
import Engine.Entity.Player;
import Engine.Entity.Tile.Tile;
import Logs.Logger;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Collisions
{
    /**
     * Checks if the given object intersects with any object from the given list
     * @param tiles list of objects to check the intersection with
     * @return {@code true} if object intersects, otherwise {@code false}
     */
    public static boolean checkWallCollision(Entity entity, List<Tile> tiles) {
        return checkWallCollision(entity.getBoundaries(), tiles);
    }

    public static void checkEnemiesVisionZoneIntersection(Player player, List<Enemy> enemies) {
        enemies.forEach(enemy -> {
            if (enemy.visionField().bounds().intersects(player.getBoundaries()) && !enemy.seeingPlayer()) {
                Logger.log(enemy + ": has visual with player!");
                enemy.setSeeingPlayer(true);
            } else {
                if (enemy.seeingPlayer() && !enemy.visionField().bounds().intersects(player.getBoundaries())) {
                    Logger.log(enemy + " lost visual with player.");
                    enemy.setSeeingPlayer(false);
                }
            }
        });

    }

    public static boolean checkWallCollision(Rectangle2D boundaries, List<Tile> tiles) {
        boolean collides = false;

        for (Tile tile : tiles) {
            if (!collides) {
                boolean intersects = boundaries.intersects(tile.getBoundaries());
                collides = intersects && tile.solid();

            }

        }
        return collides;
    }

    // Test
    public static Enemy checkBulletCollision(Bullet bullet, List<Enemy> entities) {
          for (Enemy entity : entities) {
              if (entity != bullet.source() && bullet.getBoundaries().intersects(entity.getBoundaries())) {
                  return entity;
              }
          }
        return null;
    }

    public static <T extends Item> List<Item> checkItemCollision(Entity entity, List<T> items) {
        List<Item> intersected = new ArrayList<>();
        Rectangle2D boundaries = entity.getBoundaries();

        for (T item : items) {
            if (boundaries.intersects(item.getBoundaries())) {
                intersected.add(item);
            }

        }
        return intersected;
    }
}
