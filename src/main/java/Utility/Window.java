package Utility;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Window
{
    private final Stage stage;

    /**
     * Sets up a window
     * @param title the title of the window
     * @param stage the stage
     * @param width the width of the window
     * @param height the height of the window
     * @param resizable is resizable
     */
    public Window(String title, Stage stage, double width, double height, boolean resizable) {
        this.stage = stage;
        stage.setTitle(title);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(resizable);
    }

    public double width() {
        return stage.getWidth();
    }

    public double height() {
        return stage.getHeight();
    }
    public Point2D getCenter(double d) {
        Point2D position = new Point2D(
                (width() - d)/2,
                (height() - d)/2
        );
        return position;
    }

    public void setWidth(double width) {
        stage.setWidth(width);
    }

    public void setHeight(double height) {
        stage.setHeight(height);
    }

    public void placeAtTheCenter() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();

    }
}
