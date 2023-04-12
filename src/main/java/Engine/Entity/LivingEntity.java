package Engine.Entity;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class LivingEntity extends Entity
{
    private int health;

    public LivingEntity(Image image, double x, double y)
    {
        super(image, x, y);
    }

    public int getHealth()
    {
        return health;
    }
}
