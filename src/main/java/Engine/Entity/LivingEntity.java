package Engine.Entity;

import Engine.Entity.Entity;
import javafx.geometry.Point2D;

public abstract class LivingEntity extends Entity
{
    private int health;

    public LivingEntity(Point2D position)
    {
        super(position);
    }

    public LivingEntity(double x, double y)
    {
        super(x, y);
    }

    public int getHealth()
    {
        return health;
    }
}
