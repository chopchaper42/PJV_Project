package Engine;

import Engine.Level.LevelManager;
import GUI.GUIManager;
import Utility.Window;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class CaveShooter extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        try {
            startGame(stage);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }
    }

    private void startGame(Stage stage) throws IOException {


        Window window = new Window(
                "Cave Shooter",
                stage,
                1080,
                720,
                false
        );

        LevelManager levelManager = new LevelManager(new File("./src/main/levels"));
        File level = new File("./src/main/levels/level1ForTwoPlayers.txt");
        InventoryManager.readInventory();
        GUIManager guiManager = new GUIManager(window, stage, levelManager);

//        guiManager.renderMainWindow();
        Game game = new Game(window, guiManager, stage, level, InventoryManager.getInventory());
        game.run();
    }
}
