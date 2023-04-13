package Utility;

import javafx.stage.Stage;

public class Window
{
    private final double width;// = 1080;
    private final double height;// = 720;
    private final boolean resizable;// = false;
    private final String title;// = "Cave Shooter";

    /**
     * Sets up a window
     * @param title the title of the window
     * @param width the width of the window
     * @param height the height of the window
     * @param resizable is resizable
     */
    public Window(String title, double width, double height, boolean resizable) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.resizable = resizable;
    }

    /**
     * Initializes the window's stage
     * @param stage stage
     */
    public void init(Stage stage) {
        stage.setTitle(title);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(resizable);
    }
}
