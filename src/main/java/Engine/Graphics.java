package Engine;

import Engine.Entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public final class Graphics
{
    private static final Canvas canvas = new Canvas();
    private static final GraphicsContext graphics = canvas.getGraphicsContext2D();

    /**
     * Returns the {@code GraphicsContext}
     * @return the {@code GraphicsContext}
     */
    public static GraphicsContext getGraphics()
    {
        return graphics;
    }
    /**
     * Returns the {@code Canvas} instance
     * @return {@code canvas}
     */
    public static Canvas getCanvas() {
        return canvas;
    }
}
