import Engine.Game;
import Utility.Window;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


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
        new Window(
                "Cave Shooter",
                1080,
                720,
                false
        ).init(stage);
        new Game(stage).run();
    }
}
