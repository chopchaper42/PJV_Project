package Engine;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SquareBoundaries extends Rectangle2D {
    /**
     * Creates a new instance of {@code Rectangle2D}.
     *
     * @param minX   The x coordinate of the upper-left corner of the {@code Rectangle2D}
     * @param minY   The y coordinate of the upper-left corner of the {@code Rectangle2D}
     * @param width  The width of the {@code Rectangle2D}
     * @param height The height of the {@code Rectangle2D}
     */
    public SquareBoundaries(double minX, double minY, double width, double height) {
        super(minX, minY, width, height);
    }

    public SquareBoundaries(double x, double y, double size) {
        super(x, y, size, size);
    }
}
