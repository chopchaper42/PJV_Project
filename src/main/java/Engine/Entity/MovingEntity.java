package Engine.Entity;

import javafx.scene.image.Image;

public abstract class MovingEntity extends Entity {

    /**
     * Creates an entity
     *
     * @param image entity's image
     * @param x     x coordinate
     * @param y     y coordinate
     */
    public MovingEntity(Image image, double x, double y) {
        super(image, x, y);
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
        actualizeBoundaries();
    }

    private void actualizeBoundaries() {
        setBoundaries(
                getX(), getY(),
                getImage().getWidth(), getImage().getHeight()
        );
    }
}
