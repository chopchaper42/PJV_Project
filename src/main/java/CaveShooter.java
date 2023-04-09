import Engine.Level;
import Engine.LevelReader;
import Engine.Tiles.Floor;
import Engine.Tiles.Tile;
import Utility.Window;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class CaveShooter extends Application
{
    private Canvas canvas;

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage)
    {
        Window.init(stage);
        new Game(stage).run();
    }
}
