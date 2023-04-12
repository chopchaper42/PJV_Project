package Engine;

import Engine.Entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Graphics
{
    private static final Canvas canvas = new Canvas();
    private static final GraphicsContext graphics = canvas.getGraphicsContext2D();

    public static GraphicsContext getGraphics()
    {
        return graphics;
    }
    public static Canvas getCanvas() {
        return canvas;
    }
}
