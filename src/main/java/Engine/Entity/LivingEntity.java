package Engine.Entity;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Represents an entity that has health
 */
public abstract class LivingEntity extends Entity
{
    private int health;

    /**
     * Creates an living entity
     * @param image entity's image
     * @param x x coordinate
     * @param y y coordinate
     * @param health health
     */
    public LivingEntity(Image image, double x, double y, int health)
    {
        super(image, x, y);
        this.health = health;
    }

    /**
     * @return entity's health
     */
    public int getHealth()
    {
        return health;
    }
}
