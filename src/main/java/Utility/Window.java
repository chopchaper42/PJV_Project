package Utility;

import javafx.stage.Stage;

public class Window
{
    private static final double WIDTH = 1080;
    private static final double HEIGHT = 720;
    private static final boolean RESIZABLE = false;
    private static final String TITLE = "Cave Shooter";

    public static void init(Stage stage) {
        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(RESIZABLE);
    }
}
