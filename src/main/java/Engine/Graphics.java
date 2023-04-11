package Engine;

import Engine.Entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Graphics
{
    private static final Canvas canvas = new Canvas();
    private static final GraphicsContext graphics = canvas.getGraphicsContext2D();

    public void draw(List<Entity> drawables) {
        drawables.forEach(drawable ->
                graphics.drawImage(drawable.getImage(), drawable.getX(), drawable.getY())
        );
    }

    public static GraphicsContext getGraphics()
    {
        return graphics;
    }
}
