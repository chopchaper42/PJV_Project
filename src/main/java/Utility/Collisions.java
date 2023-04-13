package Utility;

import Engine.Entity.Entity;
import Engine.Entity.Tiles.Wall;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class Collisions
{
    public static <T> boolean checkCollision(List<T> entities, Rectangle2D object) {
        boolean intersects = false;
        boolean isWall = false;
        boolean collides = false;

        for (T entity : entities) {
            if (!collides) {
                intersects = object.intersects(((Entity) entity).getBoundaries());//.intersects(object);
                isWall = entity instanceof Wall;
                collides = intersects && isWall;

            }

        }
        return collides;
    }
}
