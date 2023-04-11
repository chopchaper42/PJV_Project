package LevelEditor;

import Utility.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class Editor extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        new Window(
                "Level Editor",
                1080,
                720,
                false
        ).init(stage);
    }
}
